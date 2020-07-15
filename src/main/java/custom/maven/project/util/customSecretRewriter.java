package custom.maven.project.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.Cipher;

public class customSecretRewriter {

	Cipher cipher;
    Key key;
    

    private String tryRewrite(String s) throws IOException, InvalidKeyException {
        if (s.length()<24)
            return s;   // Encrypting "" in Secret produces 24-letter characters, so this must be the minimum length
        if (!isBase64(s))
            return s;   // decode throws IOException if the input is not base64, and this is also a very quick way to filter

        byte[] in;
        try {
            in = Base64.decode(s.toCharArray());
        } catch (IOException e) {
            return s;   // not a valid base64
        }
        cipher.init(Cipher.DECRYPT_MODE, key);
        Secret sec = Secret.tryDecrypt(cipher, in);
        if(sec!=null) // matched
            return sec.getEncryptedValue(); // replace by the new encrypted value
        else // not encrypted with the legacy key. leave it unmodified
            return s;
    }
    
    private String modifiedFrom_tryRewrite(String s) throws IOException, InvalidKeyException {
        if (s.length()<24)
            return s;   // Encrypting "" in Secret produces 24-letter characters, so this must be the minimum length
        if (!isBase64(s))
            return s;   // decode throws IOException if the input is not base64, and this is also a very quick way to filter

        byte[] in;
        try {
            in = Base64.decode(s.toCharArray());
        } catch (IOException e) {
            return s;   // not a valid base64
        }
		cipher.init(Cipher.DECRYPT_MODE, key);
        Secret sec = Secret.tryDecrypt(cipher, in);
        if(sec!=null) // matched
            return sec.getEncryptedValue(); // replace by the new encrypted value
        else // not encrypted with the legacy key. leave it unmodified
            return s;
    }

	private boolean isBase64(String s) {
		// TODO Auto-generated method stub
		return false;
	}
}
