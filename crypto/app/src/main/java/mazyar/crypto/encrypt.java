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


public class encrypt extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        ImageButton encback = (ImageButton) findViewById(R.id.enc_back);
        encback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(encrypt.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final RadioButton des = (RadioButton) findViewById(R.id.radio_des);
        final RadioButton tdes = (RadioButton) findViewById(R.id.radio_tdes);
        final RadioButton aes = (RadioButton) findViewById(R.id.radio_aes);
        final RadioButton cbc = (RadioButton) findViewById(R.id.radio_cbc);
        final RadioButton cfb = (RadioButton) findViewById(R.id.radio_cfb);
        final RadioButton ecb = (RadioButton) findViewById(R.id.radio_ecb);
        final EditText edtin = (EditText) findViewById(R.id.edit_input_enc);
        final EditText edtout = (EditText) findViewById(R.id.edit_output_enc);
        Button encbtn = (Button) findViewById(R.id.btn_enc);
        final TextView k= (TextView)findViewById(R.id.key_enc);


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

                    KeyGenerator keygenerator = KeyGenerator.getInstance(algo);
                    SecretKey myKey = keygenerator.generateKey();
                    Key = myKey;

                    k.setText(myKey.toString());
                    Cipher cipher;


                    // Create the cipher
                    cipher = Cipher.getInstance(algo + "/" + mode + "/PKCS5Padding");

                    // Initialize the cipher for encryption

                    if (("AES".equals(algo) && "CBC".equals(mode)) || ("AES".equals(algo) && "CFB".equals(mode)))
                    {
                        cipher.init(Cipher.ENCRYPT_MODE, myKey,ivspec2);
                    }

                    else if ("CBC".equals(mode)|| "CFB".equals(mode)){
                        cipher.init(Cipher.ENCRYPT_MODE, myKey,ivspec);
                    }
                    else{
                        cipher.init(Cipher.ENCRYPT_MODE, myKey);
                    }



                    //sensitive information
                    byte[] text = edtin.getText().toString().getBytes();


                    // Encrypt the text
                    textEncrypted = cipher.doFinal(text);

                    edtout.setText(textEncrypted.toString());

                    // Initialize the same cipher for decryption
                    //   cipher.init(Cipher.DECRYPT_MODE, myKey);

                    // Decrypt the text
                    //   byte[] textDecrypted = cipher.doFinal(textEncrypted);

                    //System.out.println("Text Decryted : " + new String(textDecrypted));

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    Toast.makeText(encrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                    Toast.makeText(encrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                    Toast.makeText(encrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                    Toast.makeText(encrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                    Toast.makeText(encrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                    Toast.makeText(encrypt.this, e.toString(), Toast.LENGTH_LONG).show();
                }


////////////////////////////////////////////////////////////////////////////////////////////


            }
        });
    }
}
