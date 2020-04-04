package com.mebr0.user.type;

public enum Faculty {

    FIT("FIT", "Faculty of Information Technologies"),
    FGE("FGE", "Faculty of General Education"),
    BS("BS", "Business School"),
    ISE("ISE", "International School of Economics"),
    KMA("KMA", "Kazakhstan Maritime Academy"),
    FEOGI("FEOGI", "Faculty of Energy and Oil & Gas Industry"),
    SMC("SMC", "School of Mathematics and Cybernetics"),
    FGGE("FGGE", "Faculty of Geology and Geological Exploration"),
    SCE("SCE", "School of Chemical Engineering"),
    CAE("CAE", "Center of Alternative Energy");

    private final String name;
    private final String title;

    Faculty(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getShortName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + title + ")";
    }
}
