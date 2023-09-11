package com.example.bdqlite;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bdqlite.view.AlumnsFragment;
import com.example.bdqlite.view.CareerFragment;
import com.example.bdqlite.view.FormAddCareerFragment;
import com.example.bdqlite.view.FormAddStudentFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Toolbar toolbar;
   // FloatingActionButton fab;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // fab = findViewById(R.id.fab);

        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);





        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.bottom_home){
                    openFragment(new AlumnsFragment());
                    return true;
                } else if (itemId == R.id.bottom_alumns) {
                    openFragment(new FormAddStudentFragment());
                    return true;
                }else if (itemId == R.id.bottom_carreras) {
                    openFragment(new CareerFragment());
                    return true;
                } else if (itemId == R.id.bottom_profile) {
                    openFragment(new FormAddCareerFragment());
                    return true;
                }
                return false;
            }
        });
         fragmentManager = getSupportFragmentManager();
        openFragment(new AlumnsFragment());




      /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarFormulario();
            }
        });*/




    }



      //  drawerLayout.closeDrawer(GravityCompat.START);
      //  return true;







    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
    private void mostrarFormulario() {
        // Crear una instancia del Fragment del formulario
        Fragment formFragment = new FormAddStudentFragment();

        // Iniciar la transacción de Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Reemplazar el contenido actual con el Fragment del formulario
        transaction.replace(R.id.fragment_container, formFragment);

        // Agregar la transacción a la pila de retroceso (opcional)
        transaction.addToBackStack(null);

        // Confirmar la transacción
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

