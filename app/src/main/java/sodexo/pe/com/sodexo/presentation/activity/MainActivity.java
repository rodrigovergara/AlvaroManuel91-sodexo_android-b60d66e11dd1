package sodexo.pe.com.sodexo.presentation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.ApiClient;
import sodexo.pe.com.sodexo.data.datasource.services.GpsService;
import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.data.model.TokenResponse;
import sodexo.pe.com.sodexo.domain.entity.BlogEntity;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.domain.entity.QuizEntity;
import sodexo.pe.com.sodexo.domain.entity.ReplacementCardEntity;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.BlockCardFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.BlogDetailFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.BlogListFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.DeliveryInformationFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.MundialFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.PaymentInformationSummaryFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.PromoCommerceFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.QuizDetailFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.QuizFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.ReplaceCardFragment;
import sodexo.pe.com.sodexo.presentation.fragment.menu.MenuBaseFragment;
import sodexo.pe.com.sodexo.util.AlertUtil;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.ChangePasswordCardFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.ChangePasswordWebFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.EditProfileFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.LoginIntranetFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.RegisterSmsServiceFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.WishListFragment;
import sodexo.pe.com.sodexo.presentation.fragment.menu.CommerceFragment;
import sodexo.pe.com.sodexo.presentation.fragment.menu.ConfigurationFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.IntranetOptionFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.LastMovementsFragment;
import sodexo.pe.com.sodexo.presentation.fragment.menu.LocalFragment;
import sodexo.pe.com.sodexo.presentation.fragment.menu.MainFragment;
import sodexo.pe.com.sodexo.presentation.fragment.menu.PromoDetailFragment;
import sodexo.pe.com.sodexo.presentation.fragment.menu.PromoFragment;
import sodexo.pe.com.sodexo.presentation.fragment.menu.RouletteFragment;
import sodexo.pe.com.sodexo.presentation.fragment.menu.SodexoClubFragment;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.ViewCreditFragment;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.presenter.MainPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.MainPresenterImplement;

/**
 * 42880722
 * qwerty2
 */

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;
    @BindView(R.id.nv_main)
    NavigationView nvMain;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    private ProgressCustomDialog progressCustomDialog;
    private PromoCommerceFragment promoCommerceFragment;
    private Fragment tempFragment;
    private Fragment mainFragment;
    private boolean mainInitialized = false;
    private MainPresenter presenter;
    private int currentDataValue = 0;

    private final NavigationView.OnNavigationItemSelectedListener onItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    openMain();
                    break;
                case R.id.nav_comercio:
                    presenter.showCommerces();
                    break;
                case R.id.nav_promo:
                    presenter.showPromo();
                    break;
                case R.id.nav_club:
                    openClub();
                    break;
                case R.id.nav_configuracion:
                    openConfiguration();
                    break;
                case R.id.nav_intranet:
                    presenter.showIntranet();
                    break;
                case R.id.nav_ruleta:
                    openRoullete();
                    break;
            }
            dlMain.closeDrawers();
            return true;
        }
    };
    private boolean cameFromMarker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImplement(this);
        progressCustomDialog = new ProgressCustomDialog();
        setupNavigationView();
        Intent inputIntent = getIntent();
        if (inputIntent != null) {
            if (validateGps()) {
                this.startService(new Intent(this, GpsService.class));
                String dataKey = inputIntent.getStringExtra("data_key");
                currentDataValue = inputIntent.getIntExtra("data_value", 0);
                if (dataKey != null && !TextUtils.isEmpty(dataKey)) {
                    switch (dataKey) {
                        case "MAPA":
                            openMain();
                            break;
                        case "COMERCIOS":
                            presenter.showCommerces();
                            break;
                        case "PROMOCIONES":
                            presenter.showPromo();
                            break;
                        case "RULETA":
                            openRoullete();
                            break;
                        case "SODEXO_CLUB":
                            openClub();
                            break;
                        case "CONFIG":
                            openConfiguration();
                            break;
                        case "INTRANET":
                            presenter.showIntranet();
                            break;
                        case "CONSULTA_SALDO":
                            openViewCredit();
                            break;
                        case "ULTIMOS_MOVIMIENTOS":
                            openLastMovements();
                            break;
                        case "DATOS_PERSONALES":
                            openEditProfile();
                            break;
                        case "CAMBIAR_CLAVE_TARJETA":
                            openChangePasswordCard();
                            break;
                        case "CAMBIAR_CLAVE_WEB":
                            openChangePasswordWeb();
                            break;
                        case "ALERTA_SMS":
                            openJoinService();
                            break;
                        case "WISHLIST":
                            openWishtList();
                            break;
                        case "BLOG":
                            openBlogList();
                            break;
                        case "ENCUESTAS":
                            openQuiz();
                            break;
                        default:
                            openMain();
                            break;
                    }
                } else {
                    openMain();
                }
            } else {
                startActivity(new Intent(this, SplashActivity.class));
            }

        } else {
            openMain();
        }
        if (isGooglePlayServicesAvailable(this)) {
            boolean tokenSent = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getBoolean("token_sent", false);
            if (!tokenSent) {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
                    String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
                    LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);

                    Log.d("Primero:",json);

                    if (!TextUtils.isEmpty(data.getDni())) {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Usuario", data.getDni());
                        params.put("AppToken", refreshedToken);
                        params.put("Dispositivo", "AND");
                        Call<TokenResponse> call = ApiClient.getSodexoApiClient().postAppToken(params);
                        call.enqueue(new Callback<TokenResponse>() {
                            @Override
                            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                                if (response.body().getUserId() != 0) {
                                    PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).edit().putBoolean("token_sent", true).commit();
                                }
                            }

                            @Override
                            public void onFailure(Call<TokenResponse> call, Throwable t) {

                            }
                        });
                    }
                }

                Log.d("FCM_TOKEN", "Refreshed token: " + refreshedToken);
            }
        }


    }

    public boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    private boolean validateGps() {
        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("validateGPS", "validate GPS");
            this.startService(new Intent(this, GpsService.class));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void openMain() {
        if (!mainInitialized) {
            mainInitialized = true;
            mainFragment = Fragment.instantiate(this, MainFragment.class.getName());
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, mainFragment, MainFragment.class.getName()).commit();
            tempFragment = mainFragment;
        } else {
            ((MainFragment) mainFragment).setInitializeMap(false);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, mainFragment, MainFragment.class.getName()).commit();
            tempFragment = mainFragment;
        }
    }

    @OnClick(R.id.iv_home)
    public void openCloseDrawer() {
        if (dlMain.isDrawerOpen(GravityCompat.START)) {
            dlMain.closeDrawers();
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            dlMain.openDrawer(GravityCompat.START);
        }
    }

    private void setupNavigationView() {
        nvMain.setNavigationItemSelectedListener(onItemSelectedListener);
    }

    @Override
    public void openCommerce(List<CommerceEntity> commerce) {
        if(currentDataValue!=0){
            CommerceEntity targetEntity = null;
            for (CommerceEntity tempCommerce : commerce){
                if(tempCommerce.getId()==currentDataValue) {
                    targetEntity = tempCommerce;
                    break;
                }
            }
            if(targetEntity!=null){
                openCommerce(targetEntity,false,false);
            }
            currentDataValue = 0;
        }
        else{
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(CommerceFragment.COMMERCES, (ArrayList<? extends Parcelable>) commerce);
            Fragment fragment = Fragment.instantiate(this, CommerceFragment.class.getName(), bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, CommerceFragment.class.getName()).commit();
            tempFragment = fragment;
        }

    }

    @Override
    public void openPromos(List<PromoEntity> promos) {
        if(currentDataValue!=0){
            PromoEntity targetEntity = null;
            for (PromoEntity tempPromo : promos){
                if(tempPromo.getPromoId()==currentDataValue) {
                    targetEntity = tempPromo;
                    break;
                }
            }
            if(targetEntity!=null){
                openPromoDetail(targetEntity, false);
            }
            currentDataValue = 0;
        }
        else {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(PromoFragment.PROMOS, (ArrayList<? extends Parcelable>) promos);
            Fragment fragment = Fragment.instantiate(this, PromoFragment.class.getName(), bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, PromoFragment.class.getName()).commit();
            tempFragment = fragment;
        }
    }
    @Override
    public void openCommerce(CommerceEntity commerceEntity, boolean isWishList, boolean fromMarker) {
        this.cameFromMarker = fromMarker;
        Bundle bundle = new Bundle();
        bundle.putParcelable(LocalFragment.COMMERCE, commerceEntity);
        bundle.putBoolean(LocalFragment.IS_WISHLIST, isWishList);
        Fragment fragment = Fragment.instantiate(this, LocalFragment.class.getName(), bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_container, fragment, LocalFragment.class.getName());
        if (fromMarker)
            ft.addToBackStack("wat");

        ft.commit();
        tempFragment = fragment;
    }

    @Override
    public void openClub() {
        Fragment fragment = Fragment.instantiate(this, SodexoClubFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, SodexoClubFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openConfiguration() {
        String url= obtenerURL("137358","VAMOS.PERU!!!");
        Log.d("URLE",url);

        Fragment fragment = Fragment.instantiate(this, ConfigurationFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, ConfigurationFragment.class.getName()).commit();
        tempFragment = fragment;
    }


    public String obtenerURL(String UsuarioId, String palabra)
    {
        String UsuMasVMP = UsuarioId+palabra;
        String url="";
        String UsuMD5=md5(UsuMasVMP);
        System.out.println(UsuMD5);
        url="http://sodexoclub.com.pe//Mundial/FixtureWapper?u="+UsuarioId+"&h="+UsuMD5;
        System.out.println(url);
        return url;
    }

    public String md5(String s) {
        String resultMd5 = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] result = new byte[md5.getDigestLength()];
            md5.reset();
            md5.update(s.getBytes());
            result = md5.digest();

            StringBuffer buf = new StringBuffer(result.length * 2);

            for (int i = 0; i < result.length; i++) {
                int intVal = result[i] & 0xff;
                if (intVal < 0x10) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(intVal));
            }

            resultMd5 = buf.toString().toUpperCase();
            return resultMd5;
        } catch (NoSuchAlgorithmException e) {
        }

        return resultMd5;
    }


    @Override
    public void openIntranetOption() {
        Fragment fragment = Fragment.instantiate(this, IntranetOptionFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, IntranetOptionFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openLoginIntranet() {
        Fragment fragment = Fragment.instantiate(this, LoginIntranetFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, LoginIntranetFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        if (!(tempFragment instanceof SupportMapFragment)) {
            if (!(tempFragment instanceof MainFragment)) {
                if (tempFragment instanceof PromoDetailFragment) {
                    PromoDetailFragment f = (PromoDetailFragment) tempFragment;
                    if (f.isWishList) {
                        openWishtList();
                    } else {
                        presenter.showPromo();
                    }
                } else if (tempFragment instanceof LocalFragment) {
                    if (!cameFromMarker)
                        presenter.showCommerces();
                    else {
                        super.onBackPressed();
                        tempFragment = mainFragment;
                        cameFromMarker = false;
                    }
                } else if (tempFragment instanceof MenuBaseFragment) {
                    openMain();
                } else {
                    if (tempFragment instanceof IntranetOptionFragment)
                        openMain();
                    else {
                        if (tempFragment instanceof QuizDetailFragment)
                            openQuiz();
                        else
                            openIntranetOption();
                    }
                }
            } else {
                if (dlMain.isDrawerOpen(GravityCompat.START))
                    dlMain.closeDrawer(GravityCompat.START);
                else
                    super.onBackPressed();
            }
        }


//        int count = getSupportFragmentManager().getBackStackEntryCount();
//        if (count < 1) {
//            AlertUtil.showMessageAcceptNegative(this, "", "¿Desea realmente salir de la aplicación?", "Si", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    finish();
//                    dialogInterface.dismiss();
//                }
//            }, "No", null);
//        } else {
//            getSupportFragmentManager().popBackStack();
//        }
        /*AlertUtil.showMessageAcceptNegative(this, "", "¿Desea realmente salir de la aplicación?", "Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                dialogInterface.dismiss();
            }
        }, "No", null);*/
        /*if(dlMain.isDrawerOpen(GravityCompat.START))
            dlMain.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();*/
    }

    @Override
    public void openViewCredit() {
        Fragment fragment = Fragment.instantiate(this, ViewCreditFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, ViewCreditFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openLastMovements() {
        Fragment fragment = Fragment.instantiate(this, LastMovementsFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, LastMovementsFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openEditProfile() {
        Fragment fragment = Fragment.instantiate(this, EditProfileFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, EditProfileFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openChangePasswordCard() {
        Fragment fragment = Fragment.instantiate(this, ChangePasswordCardFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, ChangePasswordCardFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openChangePasswordWeb() {
        Fragment fragment = Fragment.instantiate(this, ChangePasswordWebFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, ChangePasswordWebFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openJoinService() {
        Fragment fragment = Fragment.instantiate(this, RegisterSmsServiceFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, RegisterSmsServiceFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openWishtList() {
        Fragment fragment = Fragment.instantiate(this, WishListFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, WishListFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openPromoDetail(PromoEntity promo, boolean isWishList) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PromoDetailFragment.PROMO, promo);
        bundle.putBoolean(PromoDetailFragment.IS_WISHLIST, isWishList);
        PromoDetailFragment fragment = (PromoDetailFragment) Fragment.instantiate(this, PromoDetailFragment.class.getName(), bundle);
        fragment.isWishList = isWishList;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, PromoDetailFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void showLoading() {
        if (progressCustomDialog != null) {
            progressCustomDialog.show(getSupportFragmentManager(), ProgressCustomDialog.class.getName());
        }
    }

    @Override
    public void hideLoading() {
        if (progressCustomDialog != null) {
            progressCustomDialog.dismiss();
        }
    }

    @Override
    public void showError(String message) {
        AlertUtil.showMessageAccept(this, "", message, "Aceptar", null);
    }

    @Override
    public void openRoullete() {
        Fragment fragment = Fragment.instantiate(this, RouletteFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, RouletteFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openMainFromRoulette(int distance, int categoryId, int stars) {
        Bundle bundle = new Bundle();
        bundle.putInt(MainFragment.DISTANCE, distance);
        bundle.putInt(MainFragment.CATEGORY_ID, categoryId);
        bundle.putInt(MainFragment.STARS, stars);
        if (mainFragment == null)
            mainFragment = Fragment.instantiate(this, MainFragment.class.getName(), bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, mainFragment, MainFragment.class.getName()).commit();
        tempFragment = mainFragment;
    }

    @Override
    public void addToWishList(PromoEntity promo) {
        presenter.addToWishList(promo);
    }

    @Override
    public void deletePromoWishList(PromoEntity promo, PromoCommerceFragment pcm) {
        presenter.deletePromoToWishList(promo);
        promoCommerceFragment = pcm;
    }

    @Override
    public void deleteCommerceWishList(CommerceEntity commerce, PromoCommerceFragment pcm) {
        presenter.deleteCommerceToWishList(commerce);
        promoCommerceFragment = pcm;
    }

    @Override
    public void deleteToWishList(String message) {
        AlertUtil.showMessageAccept(this, "", message, "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                promoCommerceFragment.restartList();
                dialogInterface.dismiss();
            }
        });
    }

    @Override
    public void openBlogList() {
        Fragment fragment;
        if(currentDataValue!=0) {
            Bundle bundle = new Bundle();
            bundle.putInt("data_value",currentDataValue);
            fragment = Fragment.instantiate(this, BlogListFragment.class.getName(),bundle);
            currentDataValue = 0;
        }else
            fragment = Fragment.instantiate(this, BlogListFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, BlogListFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openQuiz() {
        Fragment fragment;
        if(currentDataValue!=0) {
            Bundle bundle = new Bundle();
            bundle.putInt("data_value",currentDataValue);
            fragment = Fragment.instantiate(this, QuizFragment.class.getName(),bundle);
            currentDataValue = 0;
        }else
            fragment = Fragment.instantiate(this, QuizFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, QuizFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openBlogDetail(BlogEntity blog) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BlogDetailFragment.BLOG, blog);
        Fragment fragment = Fragment.instantiate(this, BlogDetailFragment.class.getName(), bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, BlogDetailFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void showQuizDetail(QuizEntity quizEntity) {
        Bundle bundle = new Bundle();
        bundle.putInt(QuizDetailFragment.QUIZ_ID, quizEntity.getId());
        Fragment fragment = Fragment.instantiate(this, QuizDetailFragment.class.getName(), bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, QuizDetailFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openBlockCard() {
        Fragment fragment = Fragment.instantiate(this, BlockCardFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, BlockCardFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openReplaceCard() {
        Fragment fragment = Fragment.instantiate(this, ReplaceCardFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, ReplaceCardFragment.class.getName()).commit();
        tempFragment = fragment;
    }

    @Override
    public void openDeliveryInformation(String cardNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(DeliveryInformationFragment.CARD_NUMBER, cardNumber);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment fragment = Fragment.instantiate(this, DeliveryInformationFragment.class.getName(),bundle);

        ft.replace(R.id.fl_container, fragment);
        ft.addToBackStack(fragment.getClass().getName());
        ft.commitAllowingStateLoss();
        tempFragment = fragment;
    }

    @Override
    public void openPaymentInformationSummary(ReplacementCardEntity replacementCardEntity) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PaymentInformationSummaryFragment.REPLACEMENT_CARD, replacementCardEntity);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment fragment = Fragment.instantiate(this, PaymentInformationSummaryFragment.class.getName(),bundle);

        ft.replace(R.id.fl_container, fragment);
        ft.addToBackStack(fragment.getClass().getName());
        ft.commitAllowingStateLoss();
        tempFragment = fragment;
    }

    @Override
    public void openMundial( String algo)
    {
        Fragment fragment = Fragment.instantiate(this, MundialFragment.class.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, MundialFragment.class.getName()).commit();
        tempFragment = fragment;

    }
}
