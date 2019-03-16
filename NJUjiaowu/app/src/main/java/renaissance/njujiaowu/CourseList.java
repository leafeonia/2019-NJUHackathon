package renaissance.njujiaowu;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import renaissance.njujiaowu.MyPkg.InfoHolder;


public class CourseList extends Activity {

    private ListView courselist;
    private List<InfoHolder> mInfoList;
    private List<InfoHolder> mbackInfoList;

    private SearchView mSearchView;

    class ViewHolder{
        TextView courseName;
        TextView courseTeacher;
        TextView courseCredit;
        ImageButton courseButton;
        TextView courseTime;
        TextView courseLimit;
        TextView courseChosen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);


        courselist = (ListView)findViewById(R.id.course_list);
    }

    @Override
    protected void onResume() {
        mInfoList = new ArrayList<>();
        mbackInfoList = new ArrayList<>();
        if (InfoHolder.m_InfoList != null){
            mInfoList.addAll(InfoHolder.m_InfoList);
            mbackInfoList.addAll(InfoHolder.m_InfoList);
        }

        //setCourseList("hehehe", "haofhadf", "adfa", "13412", "aefwef", "23t2f", 0);

        courselist.setAdapter(new CourseListAdapter());
        courselist.setTextFilterEnabled(true);

        mSearchView = (SearchView) findViewById(R.id.course_list_search);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText))
                    courselist.setFilterText(newText);
                else
                    courselist.clearTextFilter();
                return false;
            }
        });

        super.onResume();
    }

    private class CourseListAdapter extends BaseAdapter implements Filterable {

        CourseListFilter mFilter;

        @Override
        public int getCount() {
            if (mInfoList == null){
                return 0;
            }
            return mInfoList.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null){
                viewHolder = new ViewHolder();
                LayoutInflater layoutInflater = LayoutInflater.from(CourseList.this);
                convertView = layoutInflater.inflate(R.layout.course_list_piece,parent,false);

                viewHolder.courseName = (TextView)convertView.findViewById(R.id.courselist_name);
                viewHolder.courseTeacher = (TextView)convertView.findViewById(R.id.courselist_teacher);
                viewHolder.courseCredit = (TextView)convertView.findViewById(R.id.courselist_credit);
                viewHolder.courseButton = (ImageButton) convertView.findViewById(R.id.courselist_button);
                viewHolder.courseTime = (TextView)convertView.findViewById(R.id.courselist_time);
                viewHolder.courseLimit = (TextView)convertView.findViewById(R.id.courselist_limist);
                viewHolder.courseChosen = (TextView)convertView.findViewById(R.id.courselist_chosen);

                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.courseName.setText(mInfoList.get(position).courseName);
            viewHolder.courseTeacher.setText("老师：" + mInfoList.get(position).courseTeacher);
            viewHolder.courseCredit.setText("学分：" + mInfoList.get(position).courseCredit);
            viewHolder.courseTime.setText(mInfoList.get(position).courseTime);
            viewHolder.courseLimit.setText("限额：" + mInfoList.get(position).courseLimit);
            viewHolder.courseChosen.setText("已选：" + mInfoList.get(position).courseChosen);
            viewHolder.courseButton.setBackgroundResource(mInfoList.get(position).recommandLevel);

            viewHolder.courseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast=Toast.makeText(getApplicationContext(), "抢课成功 d=====(￣▽￣*)b 顶", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return convertView;
        }

        @Override
        public Filter getFilter() {
            if(mFilter == null)
                mFilter = new CourseListFilter();
            return mFilter;
        }

        class CourseListFilter extends Filter{

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults result = new FilterResults();
                List<InfoHolder> list;
                if(TextUtils.isEmpty(constraint))
                    list = mbackInfoList;
                else{
                    list = new ArrayList<>();
                    for(InfoHolder course:mbackInfoList)
                        if(course.courseName.contains(constraint)
                                || course.courseTeacher.contains(constraint)
                                || course.courseCredit.contains(constraint)
                                || course.courseTime.contains(constraint)
                                || course.courseLimit.contains(constraint)
                                || course.courseChosen.contains(constraint)){
                            list.add(course);
                        }
                }

                result.values = list;
                result.count = list.size();
                return result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mInfoList = (List<InfoHolder>)results.values;
                if(results.count > 0)
                    notifyDataSetChanged();
                else
                    notifyDataSetInvalidated();
            }
        }
    }
}
