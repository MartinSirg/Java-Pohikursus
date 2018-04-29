package ee.ttu.iti0202.json;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrderProcessor {
    public enum OrderProcessorType {
        DISCOUNT_10,
        REMOVE_FIRST_ITEM,
        CALCULATE_TOTAL
    }
    private final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
    private final Gson gson = new GsonBuilder()
            .addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getName().toLowerCase().contains("fieldName");
                }

                @Override
                public boolean shouldSkipClass(Class<?> aClass) {
                    return false;
                }
            })
            .create();

    private OrderProcessorType type;

    public OrderProcessor(OrderProcessorType type) {
        this.type = type;
    }

    public String process(String jsonInput) {
        System.out.println(jsonInput);
        Order order = orderFromJson(jsonInput);
        return jsonStringFromOrder(order);
    }

    private Order orderFromJson(String json) {
        System.out.println(gson.serializeNulls());
        Order order = gson.fromJson(json, Order.class);
        return order;
    }

    private String jsonStringFromOrder(Order order) {
        String result = gson.toJson(order);
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor(OrderProcessorType.CALCULATE_TOTAL);
        String input = "{\n" +
                "  \"order_number\": 12,\n" +
                "  \"customer\": \"Ago Luberg\",\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"item_id\": \"BOX1\",\n" +
                "      \"title\": \"A box\",\n" +
                "      \"price\": 14.00,\n" +
                "      \"count\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"item_id\": \"CHOCO\",\n" +
                "      \"title\": \"Chocolate\",\n" +
                "      \"price\": 3.50\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        processor.process(input);
    }
}


