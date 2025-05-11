package Homework5;

class Computer {
    // Обязательные параметры
    private final String cpu;
    private final String ram;

    // Необязательные параметры
    private final String hdd;
    private final String gpu;
    private final boolean bluetooth;

    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.hdd = builder.hdd;
        this.gpu = builder.gpu;
        this.bluetooth = builder.bluetooth;
    }

    // Геттеры
    public String getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public String getHdd() {
        return hdd;
    }

    public String getGpu() {
        return gpu;
    }

    public boolean hasBluetooth() {
        return bluetooth;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", hdd='" + hdd + '\'' +
                ", gpu='" + gpu + '\'' +
                ", bluetooth=" + bluetooth +
                '}';
    }

    // Внутренний класс Builder
    public static class ComputerBuilder {
        // Обязательные параметры
        private final String cpu;
        private final String ram;

        // Необязательные параметры с значениями по умолчанию
        private String hdd = "1TB";
        private String gpu = "Integrated";
        private boolean bluetooth = false;

        public ComputerBuilder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public ComputerBuilder setHdd(String hdd) {
            this.hdd = hdd;
            return this;
        }

        public ComputerBuilder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public ComputerBuilder setBluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

// Пример использования
public class BuilderPattern {
    public static void main(String[] args) {
        Computer gamingComputer = new Computer.ComputerBuilder("Intel i9", "32GB")
                .setGpu("NVIDIA RTX 3080")
                .setHdd("2TB SSD")
                .setBluetooth(true)
                .build();

        System.out.println("Gaming Computer: " + gamingComputer);

        Computer officeComputer = new Computer.ComputerBuilder("Intel i5", "8GB")
                .build();

        System.out.println("Office Computer: " + officeComputer);
    }
}