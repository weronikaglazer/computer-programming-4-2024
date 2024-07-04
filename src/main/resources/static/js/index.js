const getProducts = () => {
    return fetch("/api/products")
        .then(r => r.json())
}

const getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then(r => r.json())
}

const refreshOffer = () => {
    const offerTotalEl = document.querySelector("#offer__total")
    const itemsCountEl = document.querySelector("#offer_items-count")

    getCurrentOffer()
        .then(offer => {
            offerTotalEl.textContent = `${offer.finalPrice} PLN`
            itemsCountEl.textContent = `${offer.getItemsCount()}🛒`
        })
}

const addProductToCart = (productId) => {
    return fetch(`/api/add-to-cart/${productId}`, {
        method: "POST"
    })
}

const acceptOffer = (acceptOfferRequest) => {
    return fetch("/api/accept-offer", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(acceptOfferRequest)
    })
        .then(r => r.json())
}

const createHtmlProductEl = (productData) => {
    const template = `
        <div class="product">
            <h4>${productData.name}</h4>
            <img src="https://www.buyqualityplr.com/wp-content/uploads/edd/2017/12/Good-Ecommerce.png" width=200 height=200 />
            <div class="product__price">
                <span>${productData.price}</span>
                <button data-id="${productData.id}">Add to cart</button>
            </div>
        </div>
    `
    const el = document.createElement("li");
    el.innerHTML = template.trim();

    return el;
}

const initializeCartHandler = (productHtmlEl) => {
    const addToCartButton = productHtmlEl.querySelector("button[data-id")
    addToCartButton.addEventListener("click", (event) => {
        const targetProductId = event.target.getAttribute("data-id")
        addProductToCart(targetProductId)
            .then(refreshOffer())
    })

    return productHtmlEl

}


const checkoutFormEl = document.querySelector("#checkout-form")
checkoutFormEl.addEventListener("submit", (event) => {
    event.preventDefault()
    
    const acceptOfferRequest = {
        firstName: checkoutFormEl.elements.firstname.value,
        lastName: checkoutFormEl.elements.lastname.value,
        email: checkoutFormEl.elements.email.value
    }

    acceptOffer(acceptOfferRequest)
        .then(reservationDetails => {window.location.href = reservationDetails.paymentUrl})
})

document.addEventListener("DOMContentLoaded", () => {
    const productList = document.querySelector("#productList")

    getProducts()
        .then(productsAsJson => productsAsJson.map(createHtmlProductEl))
        .then(productsAsHtml => productsAsHtml.map(initializeCartHandler))
        .then(productsAsHtml => productsAsHtml.forEach(productEl => productList.appendChild(productEl)))
})

