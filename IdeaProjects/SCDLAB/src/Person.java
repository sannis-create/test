class Person {
    private String name;
    private int age;
    private String location;
    private int scdMarks;
    private int pfMarks;

    public Person(String name, int age, String location, int scdMarks, int pfMarks) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.scdMarks = scdMarks;
        this.pfMarks = pfMarks;
    }

    public int getScdMarks() {
        return scdMarks;
    }

    public int getPfMarks() {
        return pfMarks;
    }
}