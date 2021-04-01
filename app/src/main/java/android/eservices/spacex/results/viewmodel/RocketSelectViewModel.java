package android.eservices.spacex.results.viewmodel;

import android.eservices.spacex.data.api.model.Rocket;
import android.eservices.spacex.data.repository.rocket.IRocketRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class RocketSelectViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable;
    private IRocketRepository rocketRepository;

    public RocketSelectViewModel(IRocketRepository rocketRepository) {
        this.rocketRepository = rocketRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    private MutableLiveData<List<Rocket>> rockets;
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
}