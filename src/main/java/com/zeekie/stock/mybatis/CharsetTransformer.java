package com.zeekie.stock.mybatis;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import com.zeekie.stock.util.StringUtil;

/**
 * @author zeekie
 * @version 1.0, 2013/05/10
 */
public class CharsetTransformer {

    private String clientEncoding;

    private String serverEncoding;

    public CharsetTransformer(String clientEncoding, String serverEncoding) {

        // check charset
        Charset.forName(clientEncoding);
        Charset.forName(serverEncoding);

        if (StringUtil.equalsIgnoreCase(clientEncoding, serverEncoding)) {
            throw new IllegalArgumentException("clientEncoding can't be equals to serverEncoding");
        }

        this.clientEncoding = clientEncoding;
        this.serverEncoding = serverEncoding;

    }

    public String decode(String src) {

        if (StringUtil.isNotBlank(src)) {
            try {
                src = new String(src.getBytes(serverEncoding), clientEncoding);
            } catch (UnsupportedEncodingException e) {
                ;
            }
        }

        return src;
    }

    public String encode(String src)  {

        if (StringUtil.isNotBlank(src)) {
            try {
                src = new String(src.getBytes(clientEncoding), serverEncoding);
            } catch (UnsupportedEncodingException e) {
                ;
            }
        }
        return src;
    }


    public String getClientEncoding() {
        return clientEncoding;
    }

    public void setClientEncoding(String clientEncoding) {
        this.clientEncoding = clientEncoding;
    }

    public String getServerEncoding() {
        return serverEncoding;
    }

    public void setServerEncoding(String serverEncoding) {
        this.serverEncoding = serverEncoding;
    }

}
