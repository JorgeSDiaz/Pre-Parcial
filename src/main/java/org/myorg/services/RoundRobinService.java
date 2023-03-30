package org.myorg.services;

public class RoundRobinService {
    private final int cantServers;
    private int actualServer;

    public RoundRobinService(int cantServers) {
        this.cantServers = cantServers;
        this.actualServer = 0;
    }

    public int getActualServer() {
        int server = this.actualServer;
        this.actualServer = (this.actualServer + 1) % cantServers;

        return server + 1;
    }
}
