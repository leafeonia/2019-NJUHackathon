package renaissance.njujiaowu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class homework extends AppCompatActivity {

    ListView lvToDos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lvToDos = (ListView)findViewById(R.id.toDo_listView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homework.this,homeworkItem.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Intent i = new Intent(homework.this,MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        SharedPreferences pref = getSharedPreferences("homeWorkItem",MODE_PRIVATE);
        Map<String,String> all = (Map<String,String>) pref.getAll();
        Set<Map.Entry<String,String>> entrys = all.entrySet();
        List<Map.Entry<String,String>> toDos = new ArrayList<>(entrys);

        sortToDos(toDos);

        lvToDos.setAdapter(new toDoAdapter(toDos,homework.this));

        super.onResume();
    }

    private void sortToDos(List<Map.Entry<String,String>> toDos){
        for (int i = 0; i < toDos.size(); i++) {
            for (int j = i+1; j < toDos.size(); j++) {
                String iInfo[] = toDos.get(i).getValue().split("\\|")[2].split("`");
                String jInfo[] = toDos.get(j).getValue().split("\\|")[2].split("`");
                boolean swap = false;
                for (int k = 0; k < 5; k++) {
                    if (Integer.parseInt(iInfo[k]) > Integer.parseInt(jInfo[k])){
                        swap = true;
                        break;
                    }
                    else if (Integer.parseInt(iInfo[k]) < Integer.parseInt(jInfo[k])){
                        break;
                    }
                }
                if (swap){
                    Map.Entry<String,String> entry = toDos.get(i);
                    toDos.set(i,toDos.get(j));
                    toDos.set(j,entry);
                }
            }
        }
    }
}
