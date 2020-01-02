package VertXTimeManagement.API;

import VertXTimeManagement.Storage.LoginData;
import VertXTimeManagement.Storage.PublicDataContainer;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface TimeManagementAPI {

    CompletableFuture<PublicDataContainer> getBasicInfo(final UUID uuid);

    CompletableFuture<ArrayList<LoginData>> getEachLogin(final UUID uuid);

}
