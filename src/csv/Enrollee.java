package csv;

public class Enrollee {
    String userId;

    String name;

    int version;

    String insuranceCompany;

    Enrollee(String userId, String name, int version, String insuranceCompany) {
        this.userId = userId;
        this.name = name;
        this.version = version;
        this.insuranceCompany = insuranceCompany;
    }

    /**
     * Formats a given name last name first name.  Assuming last name is last and first name is any name before last name.
     * @return String
     */
    public String formatNameForSort() {
        String[] nameTokenized = name.trim().split("\\s+");
        StringBuilder nameBuilder = new StringBuilder();

        for (int i = 0; i < nameTokenized.length - 1; i++) {
            if (i > 0) {
                // Not the first iteration; add a space to what came before.
                nameBuilder.append(' ');
            }
            nameBuilder.append(nameTokenized[i]);
        }

        return (nameTokenized[nameTokenized.length - 1] + " " + nameBuilder).trim();
    }

    @Override
    public String toString() {
        return this.userId + "," + this.name + "," + this.version + "," + this.insuranceCompany;
    }
}
