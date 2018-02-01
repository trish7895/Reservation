package com.thiman.android.reservationmanager.rest;

import com.thiman.android.reservationmanager.AvailableDetails.AvailableRoomDetailsModel;
import com.thiman.android.reservationmanager.BookingDetails.BookingDetailsModel;
import com.thiman.android.reservationmanager.postModel.CheckBookingModel;
import com.thiman.android.reservationmanager.ReportDetails.ReportModel;
import com.thiman.android.reservationmanager.ReportDetails.ReportModel2;
import com.thiman.android.reservationmanager.RoomDetails.RoomDetailsModel;
import com.thiman.android.reservationmanager.postModel.ConfirmModel;
import com.thiman.android.reservationmanager.postModel.bookingRespons;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Asus on 1/30/2018.
 */

public interface ApiInterface {
  //  @Headers("x-access-token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1hbmFnZXIiLCJpYXQiOjE1MTczMjQ1MjEsImV4cCI6MTUxNzQxMDkyMX0.QyI6uS7dpzQ9W6dGNV9n4gk6e_Dw--5PDIaAfMIUxG0")
    @GET("viewAllHotelRoomsDetails")
    Call<RoomDetailsModel>  getRoomDetails();
    @GET("viewAllRooms")
    Call<AvailableRoomDetailsModel>  getAvailableDetails();

    @GET("viewAllBandRRooms")
    Call<BookingDetailsModel>  getBookingDetails();

    @GET("roomTypeBookingReport")
    Call<ReportModel>  getReportData();
    @GET("monthlyBookingReport")
    Call<ReportModel2>  getBookingReportData();

    @POST("roomAvailabilty")
    Call<bookingRespons>  postRoomAvailabilty(@Body CheckBookingModel checkBookingModel);

    @POST("confirmCustomerBooking")
    Call<ConfirmModel>  finalPost(@Body ConfirmModel confirmModel);
}
