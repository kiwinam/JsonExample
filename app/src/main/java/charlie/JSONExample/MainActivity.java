package charlie.JSONExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int LAYOUT = R.layout.activity_main;
    private ArrayList<Item> mItems = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    private EditText mJsonNationEt;
    private EditText mJsonNameEt;
    private EditText mJsonAddressEt;
    private EditText mJsonAgeEt;

    private TextView mReceiveTv;
    private TextView mReceiveNationTv;
    private TextView mReceiveNameTv;
    private TextView mReceiveAddressTv;
    private TextView mReceiveAgeTv;

    private Button mObjectBtn;
    private Button mArrayBtn;

    private RecyclerView recyclerView;

    private LinearLayout objectResultLo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        bindView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.objectBtn:
                sendObject();
                break;
            case R.id.arrayBtn:
                sendArray();
                break;
            default:
                break;
        }
    }

    private void bindView(){
        //bind view
        mJsonNameEt = (EditText) findViewById(R.id.jsonNameEt);
        mJsonNationEt = (EditText) findViewById(R.id.jsonNationEt);
        mJsonAddressEt = (EditText) findViewById(R.id.jsonAddressEt);
        mJsonAgeEt = (EditText) findViewById(R.id.jsonAgeEt);

        mReceiveTv = (TextView) findViewById(R.id.receiveTv);
        mReceiveNationTv = (TextView) findViewById(R.id.receiveNationTv);
        mReceiveNameTv = (TextView) findViewById(R.id.receiveNameTv);
        mReceiveAddressTv = (TextView) findViewById(R.id.receiveAddressTv);
        mReceiveAgeTv = (TextView) findViewById(R.id.receiveAgeTv);

        mObjectBtn = (Button) findViewById(R.id.objectBtn);
        mArrayBtn = (Button) findViewById(R.id.arrayBtn);

        recyclerView = (RecyclerView) findViewById(R.id.resultRv);

        objectResultLo = (LinearLayout) findViewById(R.id.objectResultLo);

        recyclerView.setVisibility(View.GONE);
        objectResultLo.setVisibility(View.GONE);
        //set listener
        mObjectBtn.setOnClickListener(this);
        mArrayBtn.setOnClickListener(this);

        //set Recycler View
        adapter = new RecyclerAdapter(mItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void sendObject(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("nation", mJsonNationEt.getText().toString());
            jsonObject.put("name", mJsonNameEt.getText().toString());
            jsonObject.put("address", mJsonAddressEt.getText().toString());
            jsonObject.put("age", mJsonAgeEt.getText().toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
        receiveObject(jsonObject);
    }

    private void receiveObject(JSONObject data){
        recyclerView.setVisibility(View.GONE);
        objectResultLo.setVisibility(View.VISIBLE);
        try{
            mReceiveTv.setText(data.toString());
            mReceiveNationTv.setText("nation : "+data.getString("nation"));
            mReceiveNameTv.setText("name : "+data.getString("nation"));
            mReceiveAddressTv.setText("address : "+data.getString("address"));
            mReceiveAgeTv.setText("age : "+data.getString("age"));
        } catch (JSONException e){
            e.printStackTrace();
        }
    }


    // 서버로 배열 데이터 전송할 때
    private void sendArray(){
        JSONObject wrapObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            for(int i = 0; i < 10; i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("nation", mJsonNationEt.getText().toString());
                jsonObject.put("name", mJsonNameEt.getText().toString());
                jsonObject.put("address", mJsonAddressEt.getText().toString());
                jsonObject.put("age", mJsonAgeEt.getText().toString());
                jsonArray.put(jsonObject);
            }
            wrapObject.put("list",jsonArray);

            //실제 데이터 전송 메소드
            receiveArray(wrapObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    // 서버에서 배열 데이터를 전송 받을 때
    private void receiveArray(String dataObject){
        recyclerView.setVisibility(View.VISIBLE);
        objectResultLo.setVisibility(View.GONE);
        mItems.clear();
        try {
            // String 으로 들어온 값 JSONObject 로 1차 파싱
            JSONObject wrapObject = new JSONObject(dataObject);

            // JSONObject 의 키 "list" 의 값들을 JSONArray 형태로 변환
            JSONArray jsonArray = new JSONArray(wrapObject.getString("list"));
            for(int i = 0; i < jsonArray.length(); i++){
                // Array 에서 하나의 JSONObject 를 추출
                JSONObject dataJsonObject = jsonArray.getJSONObject(i);
                // 추출한 Object 에서 필요한 데이터를 표시할 방법을 정해서 화면에 표시
                // 필자는 RecyclerView 로 데이터를 표시 함
                mItems.add(new Item(dataJsonObject.getString("nation")+i,dataJsonObject.getString("name")+i,
                        dataJsonObject.getString("address")+i,dataJsonObject.getString("age")));
            }
            // Recycler Adapter 에서 데이터 변경 사항을 체크하라는 함수 호출
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
