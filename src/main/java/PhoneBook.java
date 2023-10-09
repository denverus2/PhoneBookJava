import java.util.*;

public class PhoneBook {

    // Создаем HashMap, где ключ - это имя человека, а значение - это HashSet из номеров телефонов
    private HashMap<String, HashSet<String>> phoneBook;

    // Конструктор класса PhoneBook
    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    // Метод для добавления нового контакта в телефонную книгу
    public void addContact(String name, String phone) {
        // Проверяем, есть ли уже такое имя в телефонной книге
        if (phoneBook.containsKey(name)) {
            // Если есть, то добавляем новый номер телефона в HashSet, связанный с этим именем
            phoneBook.get(name).add(phone);
        } else {
            // Если нет, то создаем новый HashSet с номером телефона и помещаем его в HashMap с именем в качестве ключа
            HashSet<String> phones = new HashSet<>();
            phones.add(phone);
            phoneBook.put(name, phones);
        }
    }

    // Метод для получения всех контактов из телефонной книги в виде списка строк
    public List<String> getAllContacts() {
        // Создаем список строк для хранения результатов
        List<String> result = new ArrayList<>();
        // Проходим по всем записям в HashMap
        for (Map.Entry<String, HashSet<String>> entry : phoneBook.entrySet()) {
            // Получаем имя и множество номеров телефонов для каждой записи
            String name = entry.getKey();
            HashSet<String> phones = entry.getValue();
            // Формируем строку в формате "Имя: номер1, номер2, ..."
            StringBuilder sb = new StringBuilder();
            sb.append(name).append(": ");
            for (String phone : phones) {
                sb.append(phone).append(", ");
            }
            // Удаляем последнюю запятую и пробел
            sb.delete(sb.length() - 2, sb.length());
            // Добавляем строку в список результатов
            result.add(sb.toString());
        }
        // Сортируем список по убыванию числа телефонов для каждого имени
        result.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int count1 = s1.split(",").length;
                int count2 = s2.split(",").length;
                return Integer.compare(count2, count1);
            }
        });
       return result;
    }


    public void printAllContacts() {
       List<String> contacts = getAllContacts();

        for (String contact : contacts) {
            System.out.println(contact);
        }
    }


    public static void main(String[] args) {

        PhoneBook pb = new PhoneBook();
        pb.addContact("Анна", "+7-999-111-11-11");
        pb.addContact("Борис", "+7-888-222-22-22");
        pb.addContact("Виктор", "+7-777-333-33-33");
        pb.addContact("Анна", "+7-999-444-44-44");
        pb.addContact("Дмитрий", "+7-666-555-55-55");
        pb.addContact("Виктор", "+7-777-666-66-66");
        pb.addContact("Елена", "+7-555-777-77-77");
        pb.addContact("Борис", "+7-888-888-88-88");
        // Выводим все контакты из телефонной книги на экран
        pb.printAllContacts();
    }
}