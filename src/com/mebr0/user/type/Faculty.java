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

    private final String NAME;
    private final String TITLE;

    Faculty(String name, String title) {
        this.NAME = name;
        this.TITLE = title;
    }

    public String getTitle() {
        return TITLE;
    }

    public String getShortName() {
        return NAME;
    }
}
