package sample;

public class mjesecUhrvatski {
    public String pretvoriHR (String mjesecEN) {
        if (mjesecEN.equalsIgnoreCase("january")) return "Sijecanj";
        if (mjesecEN.equalsIgnoreCase("february")) return "Veljaca";
        if (mjesecEN.equalsIgnoreCase("march")) return "Ozujak";
        if (mjesecEN.equalsIgnoreCase("april")) return "Travanj";
        if (mjesecEN.equalsIgnoreCase("may")) return "Svibanj";
        if (mjesecEN.equalsIgnoreCase("june")) return "Lipanj";
        if (mjesecEN.equalsIgnoreCase("july")) return "Srpanj";
        if (mjesecEN.equalsIgnoreCase("august")) return "Kolovoz";
        if (mjesecEN.equalsIgnoreCase("september")) return "Rujan";
        if (mjesecEN.equalsIgnoreCase("october")) return "Listopad";
        if (mjesecEN.equalsIgnoreCase("november")) return "Studeni";
        if (mjesecEN.equalsIgnoreCase("december")) return "Prosinac";

        else return null;
    }
}
