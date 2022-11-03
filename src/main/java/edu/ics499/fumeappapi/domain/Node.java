package edu.ics499.fumeappapi.domain;

public class Node{
    private String macAddress, ipAddress;


    public Node() throws IOException {
        this.setMacAddress();
        this.setIpAddress();
    }


    /**
     * @return the macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }


    /**
     * @param macAddress the macAddress to set
     * @throws UnknownHostException
     * @throws SocketException
     */
    public void setMacAddress() throws UnknownHostException, SocketException {
        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface nic = NetworkInterface.getByInetAddress(localHost);
        byte[] hardwareAddress = nic.getHardwareAddress();

        String[] hex = new String[hardwareAddress.length];
        for(int i = 0; i < hardwareAddress.length; i++) {
            hex[i] = String.format("%02X", hardwareAddress[i]);
        }
        macAddress = String.join("-", hex);
    }


    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }


    /**
     * @param ipAddress the ipAddress to set
     * @throws IOException
     */
    public void setIpAddress() throws IOException {
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            ipAddress = datagramSocket.getLocalAddress().getHostAddress();
        }
    }



}
