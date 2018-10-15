package mazyar.crypto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static mazyar.crypto.MainActivity.Key;
import static mazyar.crypto.MainActivity.ivspec;
import static mazyar.crypto.MainActivity.ivspec2;
import static mazyar.crypto.MainActivity.textEncrypted;

public class decrypt extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);
        ImageButton encback = (ImageButton) findViewById(R.id.dec_back);
        encback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(decrypt.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final RadioButton des = (RadioButton) findViewById(R.id.dec_radio_des);
        final RadioButton tdes = (RadioButton) findViewById(R.id.dec_radio_tdes);
        final RadioButton aes = (RadioButton) findViewById(R.id.dec_radio_aes);
        final RadioButton cbc = (RadioButton) findViewById(R.id.dec_radio_cbc);
        final RadioButton cfb = (RadioButton) findViewById(R.id.dec_radio_cfb);
        final RadioButton ecb = (RadioButton) findViewById(R.id.dec_radio_ecb);
        final EditText edtin = (EditText) findViewById(R.id.edit_input_dec);
        final EditText edtout = (EditText) findViewById(R.id.edit_output_dec);
        Button encbtn = (Button) findViewById(R.id.btn_dec);
        TextView kd = (TextView) findViewById(R.id.key_dec);
        if (Key != null) {
            kd.setText(Key.toString());
        }

        encbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/////////////////////////////////////////////////////////////////////////////////////////////

                try {

                    String algo = "DES";
                    String mode = "CBC";

                    if (des.isChecked()) {
                        algo = "DES";
                    } else if (tdes.isChecked()) {
                        algo = "DESede";
                    } else if (aes.isChecked()) {
                        algo = "AES";
                    }

                    if (cbc.isChecked()) {
                        mode = "CBC";
                    } else if (cfb.isChecked()) {
                        mode = "CFB";
                    } else if (ecb.isChecked()) {
                        mode = "ECB";
                    }


                    Cipher dcipher;


                    // Create the cipher
                    dcipher = Cipher.getInstance(algo + "/" + mode + "/PKCS5Padding");


                    //sensitive information


                    // Initialize the same cipher for decryption
                    if (("AES".equals(algo) && "CBC".equals(mode)) || ("AES".equals(algo) && "CFB".equals(mode)))
                    {
                        dcipher.init(Cipher.DECRYPT_MODE, Key,ivspec2);
                    }
                    else if ("CBC".equals(mode) || "CFB".equals(mode)) {
                        dcipher.init(Cipher.DECRYPT_MODE, Key, ivspec);
                    } else {
                        dcipher.init(Cipher.DECRYPT_MODE, Key);
                    }


                    // Decrypt the text
                    byte[] textDecrypted = dcipher.doFinal(textEncrypted);
                    edtout.setText(new String(textDecrypted));

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    Toast.makeText(decrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                    Toast.makeText(decrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                    Toast.makeText(decrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                    Toast.makeText(decrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                    Toast.makeText(decrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                    Toast.makeText(decrypt.this, e.toString(), Toast.LENGTH_LONG).show();

                }


////////////////////////////////////////////////////////////////////////////////////////////


            }
        });
    }
}
