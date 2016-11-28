package br.android.cericatto.phonecallback;

import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

/**
 * PhoneCallback.java.
 *
 * @author Rodrigo Cericatto
 * @since Nov 19, 2016
 */
public class PhoneCallback extends PhoneStateListener {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    public static String LOG_TAG = "PhoneCallback";

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    private TextView mTextView;
    private String mText;

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public PhoneCallback(TextView textView) {
        mTextView = textView;
    }

    //--------------------------------------------------
    // PhoneStateListener
    //--------------------------------------------------

    @Override
    public void onCellInfoChanged(List<CellInfo> cellInfo) {
        super.onCellInfoChanged(cellInfo);
        Log.i(LOG_TAG, "onCellInfoChanged: " + cellInfo);
    }

    @Override
    public void onDataActivity(int direction) {
        super.onDataActivity(direction);
        switch (direction) {
            case TelephonyManager.DATA_ACTIVITY_NONE:
                Log.i(LOG_TAG, "onDataActivity: DATA_ACTIVITY_NONE");
                break;
            case TelephonyManager.DATA_ACTIVITY_IN:
                Log.i(LOG_TAG, "onDataActivity: DATA_ACTIVITY_IN");
                break;
            case TelephonyManager.DATA_ACTIVITY_OUT:
                Log.i(LOG_TAG, "onDataActivity: DATA_ACTIVITY_OUT");
                break;
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                Log.i(LOG_TAG, "onDataActivity: DATA_ACTIVITY_INOUT");
                break;
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                Log.i(LOG_TAG, "onDataActivity: DATA_ACTIVITY_DORMANT");
                break;
            default:
                Log.w(LOG_TAG, "onDataActivity: UNKNOWN " + direction);
                break;
        }
    }

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
        Log.i(LOG_TAG, "onServiceStateChanged: " + serviceState.toString());
        Log.i(LOG_TAG, "onServiceStateChanged: getOperatorAlphaLong " + serviceState.getOperatorAlphaLong());
        Log.i(LOG_TAG, "onServiceStateChanged: getOperatorAlphaShort " + serviceState.getOperatorAlphaShort());
        Log.i(LOG_TAG, "onServiceStateChanged: getOperatorNumeric " + serviceState.getOperatorNumeric());
        Log.i(LOG_TAG, "onServiceStateChanged: getIsManualSelection " + serviceState.getIsManualSelection());
        Log.i(LOG_TAG, "onServiceStateChanged: getRoaming " + serviceState.getRoaming());

        switch (serviceState.getState()) {
            case ServiceState.STATE_IN_SERVICE:
                Log.i(LOG_TAG, "onServiceStateChanged: STATE_IN_SERVICE");
                break;
            case ServiceState.STATE_OUT_OF_SERVICE:
                Log.i(LOG_TAG, "onServiceStateChanged: STATE_OUT_OF_SERVICE");
                break;
            case ServiceState.STATE_EMERGENCY_ONLY:
                Log.i(LOG_TAG, "onServiceStateChanged: STATE_EMERGENCY_ONLY");
                break;
            case ServiceState.STATE_POWER_OFF:
                Log.i(LOG_TAG, "onServiceStateChanged: STATE_POWER_OFF");
                break;
        }
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                mText += "\nonCallStateChanged: CALL_STATE_IDLE, ";
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                mText += "\nonCallStateChanged: CALL_STATE_RINGING, ";
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                mText += "\nonCallStateChanged: CALL_STATE_OFFHOOK, ";
                break;
            default:
                mText += "\nUNKNOWN_STATE: " + state + ", ";
                break;
        }
        mText += "incomingNumber: " + incomingNumber;
        mTextView.setText(mText);
    }

    @Override
    public void onCellLocationChanged(CellLocation location) {
        super.onCellLocationChanged(location);
        if (location instanceof GsmCellLocation) {
            GsmCellLocation gcLoc = (GsmCellLocation) location;
            Log.i(LOG_TAG, "onCellLocationChanged: GsmCellLocation " + gcLoc.toString());
            Log.i(LOG_TAG, "onCellLocationChanged: GsmCellLocation getCid " + gcLoc.getCid());
            Log.i(LOG_TAG, "onCellLocationChanged: GsmCellLocation getLac " + gcLoc.getLac());
            Log.i(LOG_TAG, "onCellLocationChanged: GsmCellLocation getPsc" + gcLoc.getPsc()); // Requires min API 9
        } else if (location instanceof CdmaCellLocation) {
            CdmaCellLocation ccLoc = (CdmaCellLocation) location;
            Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation " + ccLoc.toString());
            Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation getBaseStationId " + ccLoc.getBaseStationId());
            Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation getBaseStationLatitude " + ccLoc.getBaseStationLatitude());
            Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation getBaseStationLongitude" + ccLoc.getBaseStationLongitude());
            Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation getNetworkId " + ccLoc.getNetworkId());
            Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation getSystemId " + ccLoc.getSystemId());
        } else {
            Log.i(LOG_TAG, "onCellLocationChanged: " + location.toString());
        }
    }

    @Override
    public void onCallForwardingIndicatorChanged(boolean changed) {
        super.onCallForwardingIndicatorChanged(changed);
        Log.i(LOG_TAG, "onCallForwardingIndicatorChanged: " + changed);
    }

    @Override
    public void onMessageWaitingIndicatorChanged(boolean changed) {
        super.onMessageWaitingIndicatorChanged(changed);
        Log.i(LOG_TAG, "onMessageWaitingIndicatorChanged: " + changed);
    }
}