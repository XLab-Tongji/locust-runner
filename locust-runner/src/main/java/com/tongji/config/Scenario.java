package com.tongji.config;

/**
 * @author : rain
 * @date : 2018/11/11 3:19 PM
 */
public enum Scenario {
    TEST(0, "test.py"),
    REGISTER(1, "register.py"),
    BROWSE_PAGES(2, "browsepage.py"),
    ADD_TO_CART(3, "addtocart.py"),
    PAYMENT(4, "payment.py");

    private Integer id;
    private String fileName;

    Scenario(Integer id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public static String getScenarioFileName(Integer id) {
        for (Scenario e : Scenario.values()) {
            if (id.equals(e.id)) {
                return e.fileName;
            }
        }

        return null;
    }
}
