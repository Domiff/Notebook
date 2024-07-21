import java.util.*;

class Laptop {
    
    private int ram;
    private int hdd;
    private String os;
    private String color;

    public Laptop( int ram, int hdd, String os, String color) {
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
    
    

    @Override
    public String toString() {
        return "Laptop{" +
                "ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class LaptopFilter {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>(Arrays.asList(
                new Laptop( 8, 512, "Windows", "Black"),
                new Laptop(16, 1024, "MacOS", "Silver"),
                new Laptop(8, 256, "Linux", "Black"),
                new Laptop( 16, 512, "Windows", "White"),
                new Laptop(32, 1024, "Windows", "Black")
        ));

        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> criteriaMap = new HashMap<>();
        criteriaMap.put(1, "ОЗУ");
        criteriaMap.put(2, "Объем ЖД");
        criteriaMap.put(3, "Операционная система");
        criteriaMap.put(4, "Цвет");

        Map<String, Object> filters = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        for (Map.Entry<Integer, String> entry : criteriaMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        while (true) {
            int criterion = scanner.nextInt();

            switch (criterion) {
                case 1:
                    System.out.println("Введите минимальный объем ОЗУ:");
                    filters.put("ОЗУ", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Введите минимальный объем ЖД:");
                    filters.put("Объем ЖД", scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Введите необходимую операционную систему:");
                    filters.put("Операционная система", scanner.next());
                    break;
                case 4:
                    System.out.println("Введите необходимый цвет:");
                    filters.put("Цвет", scanner.next());
                    break;
                default:
                    System.out.println("Неверный критерий");
                    break;
            }
            break;
            
            
        }

        Set<Laptop> filteredLaptops = filterLaptops(laptops, filters);
        System.out.println("Подходящие ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Object> filters) {
        Set<Laptop> result = new HashSet<>(laptops);

        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            switch (filter.getKey()) {
                case "ОЗУ":
                    int minRam = (int) filter.getValue();
                    result.removeIf(laptop -> laptop.getRam() < minRam);
                    break;
                case "Объем ЖД":
                    int minHdd = (int) filter.getValue();
                    result.removeIf(laptop -> laptop.getHdd() < minHdd);
                    break;
                case "Операционная система":
                    String os = (String) filter.getValue();
                    result.removeIf(laptop -> !laptop.getOs().equalsIgnoreCase(os));
                    break;
                case "Цвет":
                    String color = (String) filter.getValue();
                    result.removeIf(laptop -> !laptop.getColor().equalsIgnoreCase(color));
                    break;
                default:
                    break;
            }
        }

        return result;
    }
}
