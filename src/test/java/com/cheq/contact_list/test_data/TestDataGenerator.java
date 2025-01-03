package com.cheq.contact_list.test_data;

import com.github.javafaker.Faker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class TestDataGenerator {

    private static final Faker faker = new Faker();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, JsonNode> dataDictionary = new ConcurrentHashMap<>();

    /** Generates test data based on the provided fields array and returns the data group ID. */
    public static String generateTestData(String[] fields) {
        String dataGroup = UUID.randomUUID().toString();
        ObjectNode dataGroupNode = objectMapper.createObjectNode();

        for (String field : fields) {
            switch (field) {
                case "firstName":
                    dataGroupNode.put("firstName", faker.name().firstName());
                    break;
                case "lastName":
                    dataGroupNode.put("lastName", faker.name().lastName());
                    break;
                case "email":
                    dataGroupNode.put("email", faker.internet().emailAddress());
                    break;
                case "password":
                    dataGroupNode.put("password", faker.internet().password());
                    break;
                case "stAddress1":
                    dataGroupNode.put("stAddress1", faker.address().streetAddress());
                    break;
                case "phoneNumber":
                    dataGroupNode.put("phoneNumber", faker.number().digits(7));  // 7-digit phone number
                    break;
                case "postalCode":
                    dataGroupNode.put("postalCode", faker.address().zipCode());
                    break;
                case "country":
                    dataGroupNode.put("country", faker.address().country());
                    break;
                case "city":
                    dataGroupNode.put("city", faker.address().city());
                    break;
                case "state":
                    String[] states = {"California", "Texas", "Florida", "New York", "Illinois", "Pennsylvania", "Ohio", "Georgia", "North Carolina", "Michigan"};
                    String randomState = states[faker.random().nextInt(states.length)];
                    dataGroupNode.put("state", randomState);
                    break;
                case "stAddress2":
                    dataGroupNode.put("stAddress2", faker.address().secondaryAddress());
                    break;
                case "dateOfBirth":
                    Date birthDate = faker.date().birthday();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String formattedBirthDate = sdf.format(birthDate);
                    dataGroupNode.put("dateOfBirth", formattedBirthDate);
                    break;
                default:
                    break;
            }
        }

        saveToDictionary(dataGroup, dataGroupNode);

        return dataGroup;
    }

    /** Saves the generated test data to the in-memory dictionary under the specified data group. */
    private static void saveToDictionary(String dataGroup, JsonNode dataGroupNode) {
        dataDictionary.put(dataGroup, dataGroupNode);
    }

    /** Retrieves generated data from the in-memory dictionary and returns the value of a field in a data group. */
    public static String getGeneratedDataFromDictionary(String dataGroup, String fieldName) {
        JsonNode dataGroupNode = dataDictionary.get(dataGroup);

        if (dataGroupNode == null) {
            throw new IllegalArgumentException("Data group " + dataGroup + " not found in the dictionary.");
        }

        JsonNode fieldNode = dataGroupNode.get(fieldName);
        if (fieldNode == null) {
            throw new IllegalArgumentException("Field " + fieldName + " not found in the data group " + dataGroup + ".");
        }

        return fieldNode.asText();
    }

    /** Clear all stored test data from the in-memory dictionary. */
    public static void clearDataDictionary() {
        dataDictionary.clear();
    }
}
