package testProject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class ServerUtil
{

  private static final String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
  private static final Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

  public static final String long4IpV4(long ip) {
    int octet3 = (int)((ip >> 24) % 256L);
    int octet2 = (int)((ip >> 16) % 256L);
    int octet1 = (int)((ip >> 8) % 256L);
    int octet0 = (int)(ip % 256L);
    return octet3 + "." + octet2 + "." + octet1 + "." + 
      octet0;
  }

  public static final long ipV4ToLong(String ip) {
    String[] octets = ip.split("\\.");
    return ((Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16) + 
      (Integer.parseInt(octets[2]) << 8) + 
      Integer.parseInt(octets[3]));
  }

  public static final boolean isIpV4Private(String ip) {
    long address = ipV4ToLong(ip);
    return (((address >= ipV4ToLong("10.0.0.0")) && (address <= ipV4ToLong("10.255.255.255"))) || 
      ((address >= ipV4ToLong("172.16.0.0")) && (address <= ipV4ToLong("172.31.255.255"))) || (
      (address >= ipV4ToLong("192.168.0.0")) && (
      address <= ipV4ToLong("192.168.255.255"))));
  }

  public static final boolean isIpV4Valid(String ip) {
    return pattern.matcher(ip).matches();
  }

  /*public static final String getRemoteAddr(HttpServletRequest request) throws SocketException, UnknownHostException {
    String ip = null;
    boolean found = false;
    if ((ip = request.getHeader("x-forwarded-for")) != null) {
      StrTokenizer tokenizer = new StrTokenizer(ip, ",");
      while (tokenizer.hasNext()) {
        ip = tokenizer.nextToken().trim();
        if ((isIpV4Valid(ip)) && (!(isIpV4Private(ip)))) {
          found = true;
          break;
        }
      }
    }
    if (!(found)) {
      ip = request.getRemoteAddr();
      if (("127.0.0.1".equals(ip)) || ("0:0:0:0:0:0:0:1".equals(ip)))
      {
        InetAddress inetAddress = InetAddress.getLocalHost();
        ip = inetAddress.getHostAddress();

        if ((ip != null) && (ip.length() > 15) && 
          (ip.indexOf(",") > 0)) {
          ip = ip.substring(0, ip.indexOf(","));
        }
      }
    }

    return ip;
  }*/

  private static String getIpAddress() throws SocketException {
    InetAddress ip = null;
    Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
    while (networkInterfaces.hasMoreElements()) {
      NetworkInterface networkInterface = (NetworkInterface)networkInterfaces.nextElement();
      System.out.println(networkInterface.getName());
      Enumeration addresses = networkInterface.getInetAddresses();
      while (addresses.hasMoreElements()) {
        ip = (InetAddress)addresses.nextElement();
        if ((ip != null) && (ip instanceof Inet4Address)) {
          return ip.getHostAddress();
        }
      }
    }
    return null;
  }

  public static String getMacAddress(String ip) throws IOException {
    String line = null;
    String mac = null;
    Process process = Runtime.getRuntime().exec("nbtstat -a " + ip);
    InputStreamReader reader = new InputStreamReader(process.getInputStream());
    LineNumberReader lineReader = new LineNumberReader(reader);
    for (int i = 0; i < 100; ++i) {
      line = lineReader.readLine();
      if ((line == null) || 
        (line.indexOf("MAC") <= 1)) continue;
      mac = line.substring(line.indexOf("=") + 2, line.length());
      break;
    }
    return mac;
  }

  public static String getMacAddress(InetAddress addr) throws SocketException {
    if (addr != null) {
      String mac = null;
      byte[] macAddress = NetworkInterface.getByInetAddress(addr).getHardwareAddress();
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < macAddress.length; ++i) {
        if (i != 0) {
          builder.append("-");
        }

        int num = macAddress[i] & 0xFF;
        String numstr = Integer.toHexString(num);
        if (numstr.length() == 1)
          builder.append("0").append(numstr);
        else {
          builder.append(numstr);
        }
      }
      mac = builder.toString().toUpperCase();
     System.out.format("指定机器[{}]的物理地址[{}]", new Object[] { addr.getHostName(), mac });

      return mac;
    }
    return null;
  }

  public static void main(String[] args) throws Exception {
    getMacAddress(InetAddress.getLocalHost());
    String mac = getMacAddress("106.128.6.72");
    System.out.println(mac);
    String mac2 = getMacAddress("106.128.31.230");
    System.out.println(mac2);
    String mac3 = getMacAddress("106.128.31.138");
    System.out.println(mac3);
    System.out.println(getIpAddress());
  }
}