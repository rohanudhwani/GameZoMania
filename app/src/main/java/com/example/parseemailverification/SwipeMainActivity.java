package com.example.parseemailverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parseemailverification.Modelclass;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SwipeMainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Modelclass> barsColor;
    TextView textView, gamename, remaining_blocks;

    Button playButton;
    Button rules;


    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    AlertDialog.Builder alertDialog;
    AlertDialog.Builder alert;
    private Adapter adapter;

    int barscount = 5;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_main);
        textView = findViewById(R.id.textView);
        remaining_blocks = findViewById(R.id.textView1);
        gamename = findViewById(R.id.gamename);
        playButton = (Button) findViewById(R.id.play);
        rules = findViewById(R.id.rules);


        playButton.setVisibility(View.VISIBLE);
        rules.setVisibility(View.VISIBLE);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamename.setVisibility(View.GONE);
                playButton.setVisibility(View.GONE);
                rules.setVisibility(View.GONE);

                startgame(5, 30);
            }
        });

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SwipeMainActivity.this, rules.class);
                startActivity(i);
            }
        });


    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getPosition();
            switch (direction) {

                case ItemTouchHelper.LEFT: {
                    if ((barsColor.get(position).getColor()).equals("Red")) {
                        barsColor.remove(position);
                        adapter.notifyDataSetChanged();
                        count++;
                        remaining_blocks.setText(String.valueOf(barscount - count));
                        if (count == barscount) {
                            barscount += 5;
                            count = 0;
                            aCounter.cancel();
                            startgame(barscount, 30);
                        }
                    } else {
                        endthegame();
                        adapter.notifyDataSetChanged();
                        alertDialog.show();
                    }
                    break;
                }

                case ItemTouchHelper.RIGHT: {
                    if ((barsColor.get(position).getColor()).equals("Yellow")) {
                        barsColor.remove(position);
                        adapter.notifyDataSetChanged();
                        count++;
                        remaining_blocks.setText(String.valueOf(barscount - count));
                        if (count == barscount) {
                            barscount += 5;
                            count = 0;
                            aCounter.cancel();
                            startgame(barscount, 30);
                        }
                    } else {
                        endthegame();
                        adapter.notifyDataSetChanged();
                        alertDialog.show();
                    }
                    break;
                }
            }
        }
    };

    int key = 0;

    private void endthegame() {
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Oops! Wrong side! Try Again! ").setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SwipeMainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SwipeMainActivity.this, SwipeMainActivity.class);
                startActivity(i);
                key = 1;
            }
        }).setCancelable(false);
        alertDialog.create();
    }


    private void startgame(int bar, int time) {
        barsColor = new ArrayList<>();
        Random random = new Random();

        remaining_blocks.setText(String.valueOf(barscount - count));

        for (int i = 0; i < bar; i++) {
            int n = random.nextInt(2);

            if (n == 0) {
                barsColor.add(new Modelclass("Yellow"));
            } else {
                barsColor.add(new Modelclass("Red"));
            }
        }

        recyclerView = findViewById(R.id.recyclerview);
        textView = (TextView) findViewById(R.id.textView);

        adapter = new Adapter(this, barsColor);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        adapter.notifyDataSetChanged();


        aCounter.start();


    }

    CountDownTimer aCounter = new CountDownTimer(30 * 1000, 1000) {


        public void onTick(long millisUntilFinished) {
            if (key == 0) {
                NumberFormat f = new DecimalFormat("00");
                long sec = (millisUntilFinished / 1000) % 60;
                textView.setText(f.format(sec));
            }

        }


        public void onFinish() {
            if (key == 0) {
                textView.setText("00");
                Toast.makeText(SwipeMainActivity.this, "Time Up, Restart Game", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SwipeMainActivity.this, SwipeMainActivity.class);
                startActivity(i);
                finish();
            }

        }


    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SwipeMainActivity.this, GameChooseScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }



}