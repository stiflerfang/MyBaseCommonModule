package com.stifler.basecommonmodule.demo.base.config;

/**
 * Created by 7UP on 2017/7/14.
 */

public enum Env {
    DEV(
            "https://www.youpiaole.com"
    ),
    TEST(
            "https://www.youpiaole.com"
    ),
    PROD(
            "https://www.youpiaole.com"
    );

    private String ticketServer;

    private Env(String ticketServer){
        this.ticketServer = ticketServer;
    }

    public String getTicketServer() {
        return ticketServer;
    }

    public void setTicketServer(String ticketServer) {
        this.ticketServer = ticketServer;
    }
}
