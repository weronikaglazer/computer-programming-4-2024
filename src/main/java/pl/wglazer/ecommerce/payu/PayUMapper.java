package pl.wglazer.ecommerce.payu;
 
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;
import pl.wglazer.ecommerce.sales.offering.Offer;
import pl.wglazer.ecommerce.sales.reservation.AcceptOfferRequest;
 
public class PayUMapper {
 
    public OrderCreateRequest orderCreate(AcceptOfferRequest acceptOfferRequest, Offer offer) {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setNotifyUrl("https://your.eshop.com/notify")
               .setCustomerIp("127.0.0.1")
               .setMerchantPosId("300746")
               .setDescription("Order description")
               .setCurrencyCode("PLN")
               .setTotalAmount(offer.getFinalPrice().multiply(new BigDecimal(100)).intValue())
               .setExtOrderId(UUID.randomUUID().toString())
               .setBuyer(new Buyer()
                       .setEmail(acceptOfferRequest.getEmail())
                       .setFirstName(acceptOfferRequest.getFname())
                       .setLastName(acceptOfferRequest.getLname())
                       .setLanguage("pl"))
               .setProducts(Arrays.asList(
                        new Product()
                                .setName("Monstera")
                                .setUnitPrice(599900)
                                .setQuantity(3),
                        new Product()   
                                .setName("Calathea")
                                .setUnitPrice(679900)
                                .setQuantity(4)
                )).setContinueUrl("http://localhost:8888/");
 
        return request;
    }
}