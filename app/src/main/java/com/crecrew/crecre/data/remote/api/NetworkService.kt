package com.crecrew.crecre.data.remote.api

import com.crecrew.crecre.CreCreApplication.Companion.globalApplication
import com.crecrew.crecre.R
import com.crecrew.crecre.data.model.base.NullDataResponse
import com.crecrew.crecre.data.model.posts.PostsResponse
import com.crecrew.crecre.data.model.board.BoardResponse
import com.crecrew.crecre.data.model.reply.ReplyResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface NetworkService {

    //region Community
    //게시글 작성
    @Multipart
    @POST("posts")
    fun postPostContentsWrite(
        @Header("token") token : String,
        @Part("boardIdx") boardIdx : Int,
        @Part("is_anonymous") is_anonymous : Int,
        @Part("title") title: RequestBody,
        @Part("contents") contents : RequestBody,
        @Part imgs : MultipartBody.Part?
    ): Single<NullDataResponse>
    //게시글 삭제
    @DELETE("posts/{postIdx}")
    fun deletePostComments(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Single<NullDataResponse>
    //방금 막 올라온 최신글 조회
    @GET("posts/todaynew")
    fun getTodayNewPosts(
    ): Single<PostsResponse>
    //오늘뜨는 인기글 조회
    @GET("posts/todayhot")
    fun getTodayHotPosts(
    ): Single<PostsResponse>
    //게시글 조회
    @GET("posts/new")
    fun getNewPosts(
    ): Single<PostsResponse>
    //인기글 조회
    @GET("posts/hot")
    fun getHotPosts(
    ): Single<PostsResponse>
    //최신글 전체 조회
    @GET("posts/allnew")
    fun getAllNewPosts(
    ): Observable<PostsResponse>
    //인기글 전체 조회
    @GET("posts/allhot")
    fun getAllHotPosts(
    ): Observable<PostsResponse>
    //게시판 인기3개 나오는 통신
    @GET("posts/listhot/{boardIdx}")
    fun getHotPostsByBoardIdx(
        @Path("boardIdx") boardIdx : Int
    ): Observable<PostsResponse>
    //게시판 게시글 리스트 보여주기 인기3개 나오는 통신이 3개 이하면 데이터 반환하지 않음
    @GET("posts/list/{boardIdx}")
    fun getPostsByBoardIdx(
        @Path("boardIdx") boardIdx : Int
    ): Observable<PostsResponse>
    //게시글 상세보기
    @GET("posts/detail/{postIdx}")
    fun getPostDetailPostIdx(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Single<PostsResponse>
    //게시글 검색 결과
    @GET("posts/search")
    fun getPostSearchTitle(
        @Header("token") token: String,
        @Query("title") title: String,
        @Query("contents") contents : String
    ) : Single<PostsResponse>
    //좋아요 누르기
    @POST("posts/{postIdx}/like")
    fun postPostLikeOn(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Single<NullDataResponse>
    //좋아요 삭제
    @DELETE("posts/{postIdx}/unlike")
    fun deletePostLikeOff(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ): Single<NullDataResponse>
    //비추천 누르기
    @POST("posts/{postIdx}/hate")
    fun postPosthateOn(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Single<NullDataResponse>
    //비추천 취소
    @DELETE("posts/{postIdx}/unhate")
    fun deletePostunhateOff(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ): Single<NullDataResponse>

    //즐겨찾지 않은 게시판 보여주기
    @GET("boards/unlike")
    fun getUnlikedBoards(
        @Header("token") token: String
    ): Observable<BoardResponse>
    //즐겨찾는 게시판
    @GET("boards/like")
    fun getLikedBoards(
        @Header("token") token: String
    ): Observable<BoardResponse>
    //게시판 검색했을 경우
    @GET("boards/search")
    fun searchBoards(
        @Header("token") token: String,
        @Query("type") type: String,
        @Query("name") name: String
    ): Call<BoardResponse>
    //게시판 요청
    @POST("boards/request")
    fun requestBoard(
        @Header("token") token: String,
        @Body() body : JsonObject
    ): Single<NullDataResponse>
    //게시판 요청수 조회
    @GET("boards/request/{boardRequestIdx}")
    fun getRequestBoardCnt(
        @Header("token") token : String,
        @Path("boardRequestIdx") boardRequestIdx : Int
    ): Single<BoardResponse>
    //게시판 즐겨찾기
    @POST("boards/{boardIdx}/like")
    fun likeBoard(
        @Header("token") token: String,
        @Path("boardIdx") boardIdx: Int
    ): Single<NullDataResponse>
    //게시판 즐겨찾기 취소
    @DELETE("boards/{boardIdx}/unlike")
    fun cancelLikeBoard(
        @Header("token") token: String,
        @Path("boardIdx") boardIdx: Int
    ): Single<NullDataResponse>

    //댓글 작성
    @POST("replies/")
    fun writeReply(
        @Header("token") token : String,
        @Body() body : JsonObject
    ) : Single<NullDataResponse>
    //댓글 조회
    @GET("replies/{postIdx}")
    fun getReplies(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Single<ReplyResponse>
    //endregion

    companion object {
        fun create(): NetworkService {
            return Retrofit.Builder()
                .baseUrl(globalApplication.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkService::class.java)
        }
    }
}