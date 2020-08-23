package com.example.pace;

import android.app.Activity;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class WordAdapter extends ArrayAdapter<Word> {

    //Resource ID for the background color for this list of words
    private int mColorResourceId;


    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            // Get the {@link AndroidFlavor} object located at this position in the list
            Word currentWord = getItem(position);


            // Find the TextView in the list_item.xml layout with the ID version_number
            TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
            // Get the version number from the current AndroidFlavor object and
            // set this text on the number TextView
            miwokTextView.setText(currentWord.getMiwokTranslation());

            // Find the ImageView in the list_item. xml layout with the ID list_item_icon
            TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
            // Get the image resource ID from the current AndroidFlavor object and
            // set the image to iconView
            defaultTextView.setText(currentWord.getDefaultTranslation());

            ///Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            //set the ImageView to the image resource specified in the current word.
            imageView.setImageResource(currentWord.getImageResourceId());

            if(currentWord.hasImage()){
                imageView.setImageResource(currentWord.getImageResourceId());

                //Make sure the view is visible
                imageView.setVisibility(View.VISIBLE);
            } else {
                //otherwise hide the ImageView (set visibility to GONE)
                imageView.setVisibility(View.GONE);
            }

            //Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            //Find the color background color of the text container view
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            //Set the background color of thd text container View
            textContainer.setBackgroundColor(color);
            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
        }
        return listItemView;

    }
}
