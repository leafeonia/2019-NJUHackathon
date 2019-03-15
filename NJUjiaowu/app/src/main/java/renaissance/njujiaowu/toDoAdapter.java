package renaissance.njujiaowu;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class toDoAdapter extends BaseAdapter {

    List<Map.Entry<String,String>> toDos;
    Context context;

    public toDoAdapter(List<Map.Entry<String, String>> toDos, Context context) {
        this.toDos = toDos;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (toDos == null){
            return 0;
        }
        return toDos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.homework_list,parent,false);
            viewHolder.tv_name = (TextView)convertView.findViewById(R.id.list_name);
            viewHolder.tv_content = (TextView)convertView.findViewById(R.id.list_content);
            viewHolder.tv_ddl = (TextView)convertView.findViewById(R.id.list_ddl);
            viewHolder.bt_finished = (Button)convertView.findViewById(R.id.list_finished);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_name.setText(toDos.get(position).getKey());
        String[] info = toDos.get(position).getValue().split("\\|");
        viewHolder.tv_content.setText(info[0]);
        viewHolder.tv_ddl.setText(info[1]);
        viewHolder.bt_finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("homeWorkItem",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String key = toDos.get(position).getKey();
                toDos.remove(position);
                notifyDataSetChanged();
                editor.remove(key);
                editor.commit();
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tv_name,tv_content,tv_ddl;
        Button bt_finished;
    }
}
