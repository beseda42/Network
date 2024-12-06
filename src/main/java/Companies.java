import com.fasterxml.jackson.databind.JsonNode;

public class Companies {
    public static void main(String[] args) {
        // Объявление URL
        String companies_url = "https://fake-json-api.mock.beeceptor.com/companies";

        try {
            // Получаем ответ и парсим его
            JsonNode attributes = Functions.getJsonNode(companies_url);

            if (attributes != null) {
                // Заголовок
                System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
                System.out.println("Дата и время запроса: " + Functions.getCurrentDateTime());
                System.out.println("Сведения о компаниях");
                System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

                // Флаг для красивого вывода :)
                boolean f = false;
                // И сам вывод
                for (JsonNode attribute : attributes) {
                    if (f) {System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");}
                    f = true;

                    String[] fields = {"id", "name", "address", "zip", "country", "employeeCount", "industry", "marketCap", "domain", "logo", "ceoName"};
                    for (String elem : fields) {
                        System.out.println(elem.substring(0, 1).toUpperCase() + elem.substring(1) + ": " + attribute.path(elem).asText());
                    }
                }
                System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            } else {System.out.println("Данные не найдены");}
        } catch (Exception e) {System.out.println("Ошибка: " + e.getMessage());}
    }
}