package sodexo.pe.com.sodexo.presentation.fragment.menu;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.SlotEntity;
import sodexo.pe.com.sodexo.presentation.custom.ISlotMachineItem;
import sodexo.pe.com.sodexo.presentation.custom.WheelView;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;

public class RouletteFragment extends MenuBaseFragment {

    private final int SPIN_TIME = 2500;
    private final int MESSAGE_CHECK_MATCH1 = 1;
    private final int MESSAGE_CHECK_MATCH2 = 2;
    private final int MESSAGE_CHECK_MATCH3 = 3;

    private WheelView wheelView1;
    private WheelView wheelView2;
    private WheelView wheelView3;
    private List<ISlotMachineItem> slotItems1;
    private List<ISlotMachineItem> slotItems2;
    private List<ISlotMachineItem> slotItems3;
    private Random random;

    //Slot item texts
    private final SlotEntity slotItem1Texts[] = new SlotEntity[]{
            new SlotEntity(1000, "1 KM"),
            new SlotEntity(5000, "5 KM"),
            new SlotEntity(10000, "10 KM"),
            new SlotEntity(15000, "15 km")
    };

    private final SlotEntity slotItem2Texts[] = new SlotEntity[]{
            new SlotEntity(1, "Entretenimiento"),
            new SlotEntity(2, "Estadías y Viajes"),
            new SlotEntity(3, "Restaurantes"),
            new SlotEntity(4, "Salud y Belleza"),
            new SlotEntity(5, "Compras"),
            new SlotEntity(6, "Alimentación saludable")
    };

    private final SlotEntity slotItem3Texts[] = new SlotEntity[]{
            new SlotEntity(1, "1"),
            new SlotEntity(2, "2"),
            new SlotEntity(3, "3"),
            new SlotEntity(4, "4"),
            new SlotEntity(5, "5")
    };

    private int wheel1Selection;
    private int wheel2Selection;
    private int wheel3Selection;

    Button spin;
    private MainView mainView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_roulette, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        random = new Random();

        wheelView1 = (WheelView) view.findViewById(R.id.wheel1);
        wheelView2 = (WheelView) view.findViewById(R.id.wheel2);
        wheelView3 = (WheelView) view.findViewById(R.id.wheel3);

        //Build the slot items for each wheel.
        slotItems1 = new ArrayList<>();
        slotItems2 = new ArrayList<>();
        slotItems3 = new ArrayList<>();

        //Set the slot items for each wheels.
        slotItems1.add(new SlotItemsImpl(1, 0));
        slotItems1.add(new SlotItemsImpl(1, 1));
        slotItems1.add(new SlotItemsImpl(1, 2));
        slotItems1.add(new SlotItemsImpl(1, 3));
        wheelView1.setSlotItems(slotItems1);

        slotItems2.add(new SlotItemsImpl(2, 0));
        slotItems2.add(new SlotItemsImpl(2, 1));
        slotItems2.add(new SlotItemsImpl(2, 2));
        slotItems2.add(new SlotItemsImpl(2, 3));
        slotItems2.add(new SlotItemsImpl(2, 4));
        slotItems2.add(new SlotItemsImpl(2, 5));
        wheelView2.setSlotItems(slotItems2);

        slotItems3.add(new SlotItemsImpl(3, 0));
        slotItems3.add(new SlotItemsImpl(3, 1));
        slotItems3.add(new SlotItemsImpl(3, 2));
        slotItems3.add(new SlotItemsImpl(3, 3));
        slotItems3.add(new SlotItemsImpl(3, 4));
        wheelView3.setSlotItems(slotItems3);

        wheelView1.setNumberOfVisibleItems(3);
        wheelView2.setNumberOfVisibleItems(3);
        wheelView3.setNumberOfVisibleItems(3);
        wheelView1.setScrollFinishedListener(new WheelView.OnScrollFinishedListener() {
            @Override
            public void onWheelFinishedScrolling(int position) {
                wheel1Selection = position;
            }
        });

        wheelView2.setScrollFinishedListener(new WheelView.OnScrollFinishedListener() {
            @Override
            public void onWheelFinishedScrolling(int position) {
                wheel2Selection = position;
            }
        });

        wheelView3.setScrollFinishedListener(new WheelView.OnScrollFinishedListener() {
            @Override
            public void onWheelFinishedScrolling(int position) {
                wheel3Selection = position;
            }
        });

        spin = (Button) view.findViewById(R.id.spin);

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin.setEnabled(false);

                // Vary the time and distance range to obtain
                // randomness of wheel scrolling.
                int randomMultipler = random.nextInt(9);
                wheelView1.scroll(4000 + (100 * randomMultipler), SPIN_TIME);

//                randomMultipler = random.nextInt(9);
//                wheelView2.scroll(4000 + (100 * randomMultipler), SPIN_TIME);
//
//                randomMultipler = random.nextInt(9);
//                wheelView3.scroll(4000 + (100 * randomMultipler), SPIN_TIME);

                Message msg = Message.obtain();
                msg.what = MESSAGE_CHECK_MATCH1;
                detectAnyMatchHandler1.sendMessageDelayed(msg, SPIN_TIME + 1000);
            }
        });

        // Set the width of the Wheels based on total screen width.
        // So that it is well calculated on all devices.
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        final float density = getResources().getDisplayMetrics().density;
        int marginBetweenWheels = (int) (5 * density + 0.5f);
        int wheelWidth;
        int wheelHeight;
        boolean isPortrait = false;
        ;

        if (width > height) {
            //Landscape: Take 70% of screen width
            width = (width * 65) / 100;
            wheelHeight = (height * 60) / 100;
        } else {
            isPortrait = true;
            //Portrait: Take 90% of screen width
            width = (width * 90) / 100;
            wheelHeight = (height * 40) / 100;
        }

        //Subtract the margin between two wheels
        width -= (2 * marginBetweenWheels);

        //Divide the total number of wheels ( which is 3 for this slot machine)
        wheelWidth = width / 3;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) wheelView1.getLayoutParams();
        params.width = wheelWidth;
        params.height = wheelHeight;

        params = (LinearLayout.LayoutParams) wheelView2.getLayoutParams();
        params.width = wheelWidth;
        params.height = wheelHeight;
        params.leftMargin = marginBetweenWheels;

        params = (LinearLayout.LayoutParams) wheelView3.getLayoutParams();
        params.width = wheelWidth;
        params.height = wheelHeight;
        params.leftMargin = marginBetweenWheels;

        View thickLine1 = (View) view.findViewById(R.id.view_1);
        View thickLine2 = (View) view.findViewById(R.id.view_2);
        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) thickLine1.getLayoutParams();
        params1.width = width + (2 * marginBetweenWheels);

        params1 = (RelativeLayout.LayoutParams) thickLine2.getLayoutParams();
        params1.width = width + (2 * marginBetweenWheels);

        if (isPortrait == false) {
            //If landscape set the width for result layout
            params1 = (RelativeLayout.LayoutParams) ((View) view.findViewById(R.id.slotSpinLayout)).getLayoutParams();
            params1.width = wheelWidth + wheelWidth / 2;
        }
    }

    private Handler detectAnyMatchHandler1 = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_CHECK_MATCH1) {
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_slot_1);
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.boton_candado_abierto));
                int randomMultipler = random.nextInt(9);
                wheelView2.scroll(4000 + (100 * randomMultipler), SPIN_TIME);
                Message m1 = Message.obtain();
                m1.what = MESSAGE_CHECK_MATCH2;
                detectAnyMatchHandler2.sendMessageDelayed(m1, SPIN_TIME + 1000);
            }
        }
    };
    private Handler detectAnyMatchHandler2 = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_CHECK_MATCH2) {
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_slot_2);
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.boton_candado_abierto));
                int randomMultipler = random.nextInt(9);
                wheelView3.scroll(4000 + (100 * randomMultipler), SPIN_TIME);
                Message m2 = Message.obtain();
                m2.what = MESSAGE_CHECK_MATCH3;
                detectAnyMatchHandler3.sendMessageDelayed(m2, SPIN_TIME + 1000);
            }
        }
    };
    private Handler detectAnyMatchHandler3 = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_CHECK_MATCH3) {
                spin.setEnabled(true);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_slot_3);
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.boton_candado_abierto));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainView.openMainFromRoulette(slotItem1Texts[wheel1Selection-1].getId(), slotItem2Texts[wheel2Selection-1].getId(), slotItem3Texts[wheel3Selection-1].getId());
                    }
                }, 1500);
            }
        }
    };

    class SlotItemsImpl implements ISlotMachineItem {

        int wheelPos;
        int slotItemPos;

        public SlotItemsImpl(
                int wheelPos,
                int slotItemPos) {
            this.wheelPos = wheelPos;
            this.slotItemPos = slotItemPos;
        }

        @Override
        public View getView() {
            View view = (View) getActivity().getLayoutInflater().inflate(R.layout.item_slot, null, false);
            TextView itemTextView = (TextView) view.findViewById(R.id.itemTxt);

            Resources resources = getResources();
            if (wheelPos == 1) {
                itemTextView.setText(slotItem1Texts[slotItemPos].getDescription());
            } else if (wheelPos == 2) {
                itemTextView.setText(slotItem2Texts[slotItemPos].getDescription());
            } else if (wheelPos == 3) {
                itemTextView.setText(slotItem3Texts[slotItemPos].getDescription());
            }
            return view;
        }
    }
}
