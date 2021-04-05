package android.eservices.spacex.presentation.viewmodel;

import android.eservices.spacex.data.api.model.rocket.Rocket;
import android.eservices.spacex.data.repository.rocket.IRocketRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class RocketViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable;
    private IRocketRepository rocketRepository;

    public RocketViewModel(IRocketRepository rocketRepository) {
        this.rocketRepository = rocketRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    private MutableLiveData<List<Rocket>> rockets;
    private MutableLiveData<Rocket> rocket;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<List<Rocket>> getRockets() {
        isDataLoading.postValue(true);
        if(this.rockets == null){
            rockets = new MutableLiveData<>();
            compositeDisposable.clear();
            compositeDisposable.add(rocketRepository.getAllRockets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Rocket>>() {
                    @Override
                    public void onSuccess(@NonNull List<Rocket> rocketList) {
                        rockets.setValue(rocketList);
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                    }
                }));
        }
        return rockets;
    }

    public MutableLiveData<Rocket> getOneRocket(String rocketId) {
        isDataLoading.postValue(true);
        if(this.rocket == null){
            rocket = new MutableLiveData();
            compositeDisposable.clear();
            compositeDisposable.add(rocketRepository.getOneRocket(rocketId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Rocket>() {
                        @Override
                        public void onSuccess(@NonNull Rocket rocket) {
                            isDataLoading.setValue(false);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            System.out.println(e.toString());
                            isDataLoading.setValue(false);
                        }
                    }));
        }
        return rocket;
    }
}