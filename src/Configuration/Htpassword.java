package Configuration;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;

public class Htpassword extends ConfigurationReader {
    private HashMap<String, String> passwords;

    public Htpassword( String filename ) throws IOException {
        super( filename );
        System.out.println( "Password file: " + filename );

        this.passwords = new HashMap<String, String>();
        this.load();
    }

    protected void parseLine( String line ) {
        String[] tokens = line.split( ":" );

        if( tokens.length == 2 ) {
            passwords.put( tokens[ 0 ], tokens[ 1 ].replace( "{SHA}", "" ).trim() );
        }
    }

    public boolean isAuthorized( String authInfo ) {
        // authInfo is provided in the header received from the client
        // as a Base64 encoded string.
        String credentials = new String(
                Base64.getDecoder().decode( authInfo ),
                Charset.forName( "UTF-8" )
        );

        // The string is the key:value pair username:password
        String[] tokens = credentials.split( ":" );

        if(tokens.length > 2){
            return false;
        }
        return verifyPassword(tokens[0],tokens[1]);
    }

    private boolean verifyPassword( String username, String password ) {
        Object key1 = encryptClearPassword(password);
        Object key2 = passwords.get(username);

        if(key1.equals(key2)) {
            return true;
        }
        else{
            return false;
        }

    }

    private String encryptClearPassword( String password ) {

        try {
            MessageDigest mDigest = MessageDigest.getInstance( "SHA-1" );
            byte[] result = mDigest.digest( password.getBytes() );

            return Base64.getEncoder().encodeToString( result );
        } catch( Exception e ) {
            return "";
        }
    }

    public void load(){

    }
}
