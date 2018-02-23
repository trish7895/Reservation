package com.thiman.android.reservationmanager.rest;

import com.thiman.android.reservationmanager.AvailableDetails.AvailableRoomDetailsModel;
import com.thiman.android.reservationmanager.BookingDetails.BookingDetailsModel;
import com.thiman.android.reservationmanager.ReportDetails.ReportModel3;
import com.thiman.android.reservationmanager.postModel.CheckBookingModel;
import com.thiman.android.reservationmanager.ReportDetails.ReportModel;
import com.thiman.android.reservationmanager.ReportDetails.ReportModel2;
import com.thiman.android.reservationmanager.RoomDetails.RoomDetailsModel;
import com.thiman.android.reservationmanager.postModel.ConfirmModel;
import com.thiman.android.reservationmanager.postModel.ConfirmResponce;
import com.thiman.android.reservationmanager.postModel.bookingRespons;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

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

    @GET("countryBookingReport")
    Call<ReportModel3>  getReportCountryData();

    @POST("roomAvailabilty")
    Call<bookingRespons>  postRoomAvailabilty(@Body CheckBookingModel checkBookingModel);

    @POST("confirmCustomerBooking")
    Call<ResponseBody>  finalPost(@Body ConfirmModel confirmModel);
}
