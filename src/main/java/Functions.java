import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Functions {
    /**
     * Парсинг JSON ответа с указанного URL в JsonNode
     * @param url URL
     *            <br>String
     * @return Данные, полученные в ответ
     *            <br>JsonNode
     * @throws MalformedURLException Ошибка формата URL
     * @throws IOException Ошибка при чтении ответа
     */
    public static JsonNode getJsonNode(String url) throws IOException {

        JsonNode attributes = null;
        try {
            // Получение ответа
            String response = getString(new URL(url));
            // Парсинг JSON
            attributes = new ObjectMapper().readTree(response);
        } catch (MalformedURLException e){System.out.println("Ошибка формата URL: " + e.getMessage());
        } catch (IOException e){System.out.println("Ошибка при чтении ответа: " + e.getMessage());
        }

        return attributes;
    }

    /**
     * Отправляет GET запрос по указанному URL и возвращает ответ виде строки
     * @param url URL
     *            <br>URL
     * @return Строка с ответом
     *            <br>String
     * @throws IOException Ошибка при чтении ответа
     */
    public static String getString(URL url) throws IOException {
        // Создание соединения
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // GET-запрос
        connection.setRequestMethod("GET");
        // Считывание ответа
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String content = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        // Закрытие потока и соединения
        reader.close();
        connection.disconnect();

        return content;
    }

    /**
     * @return Текущие дата и время в формате dd.mm.yyyy hh:mm:ss
     *            <br>String
     */
    public static String getCurrentDateTime() {
        // Получение текущего времени и преобразование к нужному формату
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return currentDateTime.format(formatter);
    }
}
