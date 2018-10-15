package mazyar.crypto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class MainActivity extends AppCompatActivity {

    public static SecretKey Key;
    public final static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8};
    public final static byte[] iv2 = { 1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,13,14,15,16};
    public static final IvParameterSpec ivspec = new IvParameterSpec(iv);
    public static final IvParameterSpec ivspec2 = new IvParameterSpec(iv2);
    public static byte[] textEncrypted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button encrypt = (Button)findViewById(R.id.encrypt_page_button);
        Button decrypt = (Button)findViewById(R.id.decrypt_page_button);

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, encrypt.class);
                startActivity(intent);
            }
        });
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, decrypt.class);
                startActivity(intent);
            }
        });
    }
}
