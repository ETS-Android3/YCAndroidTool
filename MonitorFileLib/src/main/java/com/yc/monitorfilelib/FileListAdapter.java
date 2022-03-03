package com.yc.monitorfilelib;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yc.eastadapterlib.BaseRecycleAdapter;
import com.yc.eastadapterlib.BaseViewHolder;
import java.io.File;


/**
 * <pre>
 *     author : 杨充
 *     email  : yangchong211@163.com
 *     time   : 2021/8/11
 *     desc   : 文件管理适配器
 *     revise :
 * </pre>
 */
public class FileListAdapter extends BaseRecycleAdapter<File> {
    

    public FileListAdapter(Context context) {
        super(context, R.layout.item_list_file_view);
    }

    @Override
    protected void bindData(BaseViewHolder holder, File file) {
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvDate = holder.getView(R.id.tv_date);
        TextView tvSize = holder.getView(R.id.tv_size);
        ImageView ivMore = holder.getView(R.id.iv_more);

        tvName.setText(file.getName());
        tvDate.setText(FileExplorerUtils.getFileTime(file));
        if (file.isDirectory()) {
            ivIcon.setImageResource(R.drawable.sand_box_dir_icon);
            ivMore.setVisibility(View.VISIBLE);
            tvSize.setVisibility(View.GONE);
        } else {
            if (FileExplorerUtils.getSuffix(file).equals("jpg")) {
                ivIcon.setImageResource(R.drawable.sand_box_jpg_icon);
            } else if (FileExplorerUtils.getSuffix(file).equals("txt")) {
                ivIcon.setImageResource(R.drawable.sand_box_txt_icon);
            } else if (FileExplorerUtils.getSuffix(file).equals("db")) {
                ivIcon.setImageResource(R.drawable.sand_box_file_db);
            } else {
                ivIcon.setImageResource(R.drawable.sand_box_file_icon);
            }
            ivMore.setVisibility(View.GONE);
            tvSize.setVisibility(View.VISIBLE);
            long directorySize = FileExplorerUtils.getDirectorySize(file);
            SpannableString printSizeForSpannable = FileExplorerUtils.getPrintSizeForSpannable(directorySize);
            tvSize.setText(printSizeForSpannable);
        }
    }

}
