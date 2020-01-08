package top.liziyang.likebutterknife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import top.liziyang.likebutterknife.butter_knife.BindView;
import top.liziyang.likebutterknife.butter_knife.ButterKnife;
import top.liziyang.likebutterknife.butter_knife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @OnClick(R.id.button)
    private void click() {
        Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
        button.setText("Click");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }
}
