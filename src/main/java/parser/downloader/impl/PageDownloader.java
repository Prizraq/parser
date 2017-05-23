package parser.downloader.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parser.downloader.IDownloader;

public class PageDownloader implements IDownloader {

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1";
    private static final int CONNECTION_TIMEOUT = Integer.parseInt(System.getProperty("CONNECTION_TIMEOUT", "10000"));
    
    public static final Charset UTF8 = Charset.forName("utf-8");
    public static final Charset CP1251 = Charset.forName("windows-1251");
    
    private Charset charset;
    private Proxy proxy;
    private Map<String, String> requestHeaders;
    
	public PageDownloader(Charset charset, Proxy proxy, Map<String, String> requestHeaders) {
		this.charset = charset;
		this.proxy = proxy;
		this.requestHeaders = requestHeaders;
	}
	
	public String downloadPage(String url) {
		return download(url, charset, proxy, requestHeaders != null ? requestHeaders : getRequestHeaders(url));
	}
    
    public String download(String url, Charset charset, Proxy proxy, Map<String, String> requestHeaders)
    {
        System.out.println("Start to download page from url " + url);
        InputStream is = null;
        URLConnection conn = null;
        try
        {
            conn = getConnection(new URL(url), CONNECTION_TIMEOUT, proxy);
            
            if (requestHeaders != null)
            {
                for (Entry<String, String> entry : requestHeaders.entrySet())
                {
                    conn.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            
            Map<String, List<String>> responseHeaders = new HashMap<String, List<String>>();
            responseHeaders.putAll(conn.getHeaderFields());
            
            is = conn.getInputStream();
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte [] buffer = new byte[1024];
            int numRead;
            while ((numRead = is.read(buffer)) != -1)
            {
                if (out != null)
                {
                    out.write(buffer, 0, numRead);
                }
            }
            return new String(out.toByteArray(), charset);
            
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    private Map<String, String> getRequestHeaders(String url)
    {
        Map<String, String> requestHeaders = new HashMap<String, String>(3);
        requestHeaders.put("Accept-Language", "ru");
        requestHeaders.put("User-Agent", USER_AGENT);
        if (url != null && !url.isEmpty())
        {
            requestHeaders.put("Referer", url);
        }
        return requestHeaders;
    }

    private URLConnection getConnection(URL url, int timeout, Proxy proxy) throws IOException
    {
        URLConnection conn = proxy != null ? url.openConnection(proxy) : url.openConnection();
        if (timeout > 0)
        {
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
        }
        return conn;
    }
	
	

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
}
