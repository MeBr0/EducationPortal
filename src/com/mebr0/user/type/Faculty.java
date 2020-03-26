package com.mebr0.user.type;

public enum Faculty {

    FIT("Faculty of Information Technologies"),
    FGE("Faculty of General Education"),
    BS("Business School"),
    ISE("International School of Economics"),
    KMA("Kazakhstan Maritime Academy"),
    FEOGI("Faculty of Energy and Oil & Gas Industry"),
    SMC("School of Mathematics and Cybernetics"),
    FGGE("Faculty of Geology and Geological Exploration"),
    SCE("School of Chemical Engineering"),
    CAE("Center of Alternative Energy");

    private final String TITLE;

    Faculty(String title) {
        this.TITLE = title;
    }

    public String getTitle() {
        return TITLE;
    }
}
