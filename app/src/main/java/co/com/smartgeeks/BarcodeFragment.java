package co.com.smartgeeks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderFragment;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BarcodeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BarcodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarcodeFragment extends Fragment implements BarcodeReaderFragment.BarcodeReaderListener {

    private static final String TAG = BarcodeFragment.class.getSimpleName();
    private BarcodeReaderFragment barcodeReader;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    private OnFragmentInteractionListener mListener;

    public BarcodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BarcodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarcodeFragment newInstance(String param1, String param2) {
        BarcodeFragment fragment = new BarcodeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_barcode, container, false);

        barcodeReader = (BarcodeReaderFragment) getChildFragmentManager().findFragmentById(R.id.barcode_fragment);
        barcodeReader.setListener(this);
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onScanned(Barcode barcode) {
        Log.e(TAG, "onScanned: " + barcode.displayValue);
        barcodeReader.playBeep();
        Toast.makeText(getActivity(), "Barcode: " + barcode.displayValue, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

            Log.e(TAG, "onScannedMultiple: " + barcodes.size());

            String codes = "";
            for (Barcode barcode : barcodes) {
                codes += barcode.displayValue + ", ";
            }

            final String finalCodes = codes;
            Toast.makeText(getActivity(), "Barcodes: " + finalCodes, Toast.LENGTH_SHORT).show();

        }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        Log.e(TAG, "onScanError: " + errorMessage);
    }

    @Override
    public void onCameraPermissionDenied() {

        Toast.makeText(getActivity(), "Camera permission denied!", Toast.LENGTH_LONG).show();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
