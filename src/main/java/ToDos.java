import com.fasterxml.jackson.databind.JsonNode;

public class ToDos {
    public static void main(String[] args) {
        // Объявление URL
        String companies_url = "https://dummy-json.mock.beeceptor.com/todos";

        try {
            // Получаем ответ и парсим его
            JsonNode attributes = Functions.getJsonNode(companies_url);

            if (attributes != null) {
                // Заголовок
                System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
                System.out.println("Дата и время запроса: " + Functions.getCurrentDateTime());
                System.out.println("Лист To Do заданий");
                System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

                // Флаг для красивого вывода :)
                boolean f = false;
                // И сам вывод
                for (JsonNode attribute : attributes) {
                    if (f) {System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");}
                    f = true;

                    String[] fields = {"userId", "id", "title", "completed"};
                    for (String elem : fields) {
                        System.out.println(elem.substring(0, 1).toUpperCase() + elem.substring(1) + ": " + attribute.path(elem).asText());
                    }
                }
                System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            } else {System.out.println("Данные не найдены");}
        } catch (Exception e) {System.out.println("Ошибка: " + e.getMessage());}
    }
}
