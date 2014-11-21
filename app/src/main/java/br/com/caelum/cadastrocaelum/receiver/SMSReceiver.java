package br.com.caelum.cadastrocaelum.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.cadastrocaelum.dao.AlunoDAO;

/**
 * Created by jefrsilva on 21/11/14.
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] messages = (Object[]) bundle.get("pdus");
        byte[] message = (byte[]) messages[0];
        SmsMessage sms = SmsMessage.createFromPdu(message);

        AlunoDAO dao = new AlunoDAO(context);
        if (dao.isAluno(sms.getDisplayOriginatingAddress())) {
//            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
//            mp.start();

            Toast.makeText(context, "Chegou um SMS do número " + sms.getDisplayOriginatingAddress(), Toast.LENGTH_LONG).show();
        }
        dao.close();
    }
}
