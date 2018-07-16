package com.github.masaliev.guardianclient.data.remote.helper;

import android.util.Log;

import com.github.masaliev.guardianclient.data.model.news_element.NewsBody;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementEmbed;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementImage;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementRichLink;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementText;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementType;
import com.github.masaliev.guardianclient.data.model.news_element.NewsElementVideo;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class NewsBodyAdapter implements JsonDeserializer<NewsBody> {

    @Override
    public NewsBody deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(json == null || json.isJsonNull() || !json.isJsonObject()){
            return null;
        }

        JsonObject blocks = json.getAsJsonObject();
        if(!blocks.has("body")){
            return null;
        }

        JsonArray body = blocks.getAsJsonArray("body");
        if (body == null || body.size() == 0){
            return null;
        }

        JsonObject firstOfBody = body.get(0).getAsJsonObject();
        if(firstOfBody == null || !firstOfBody.has("elements")){
            return null;
        }

        JsonArray elements = firstOfBody.getAsJsonArray("elements");
        if(elements == null || elements.size() == 0){
            return null;
        }

        NewsBody newsBody = new NewsBody();
        NewsElementType newsElementType;
        JsonObject data;
        for (JsonElement jsonElement : elements){
            if (!jsonElement.isJsonObject()){
                continue;
            }
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if(!jsonObject.has("type")){
                continue;
            }

            String type = jsonObject.get("type").getAsString();
            newsElementType = NewsElementType.parse(type);
            if(newsElementType == null){
                Log.d("NEWS_BODY", jsonObject.get("type").getAsString());
                Log.d("NEWS_BODY", jsonObject.toString());
                continue;
            }
            if(newsElementType == NewsElementType.TEXT){
                data = jsonObject.getAsJsonObject("textTypeData");
                if (data == null || !data.has("html")){
                    continue;
                }
                newsBody.add(new NewsElementText(data.get("html").getAsString()));
            }else if (newsElementType == NewsElementType.RICH_LINK){
                data = jsonObject.getAsJsonObject("richLinkTypeData");
                if (data == null){
                    continue;
                }
                newsBody.add(new NewsElementRichLink(
                        data.get("url").getAsString(),
                        data.get("linkText").getAsString(),
                        data.get("linkPrefix").getAsString()));
            }else if (newsElementType == NewsElementType.IMAGE){
                JsonArray assets = jsonObject.getAsJsonArray("assets");
                if(assets == null || assets.size() == 0){
                    continue;
                }
                data = jsonObject.getAsJsonObject("imageTypeData");
                if(data == null){
                    continue;
                }
                newsBody.add(new NewsElementImage(
                        data.get("caption").getAsString(),
                        assets.get(0).getAsJsonObject().get("file").getAsString(),
                        data.get("credit").getAsString(),
                        data.get("displayCredit").getAsBoolean()
                ));
            } else if(newsElementType == NewsElementType.EMBED){
                data = jsonObject.getAsJsonObject("embedTypeData");
                if (data == null || !data.has("html")){
                    continue;
                }
                newsBody.add(new NewsElementEmbed(data.get("html").getAsString()));
            }else if(newsElementType == NewsElementType.VIDEO){
                data = jsonObject.getAsJsonObject("videoTypeData");
                if (data == null || !data.has("source")){
                    continue;
                }
                NewsElementVideo.VideoSource videoSource = NewsElementVideo.VideoSource.parse(data.get("source").getAsString());
                if (videoSource == NewsElementVideo.VideoSource.YOUTUBE){
                    newsBody.add(new NewsElementVideo(
                            data.get("url").getAsString(),
                            data.get("html").getAsString(),
                            videoSource
                    ));
                }else if (videoSource == NewsElementVideo.VideoSource.GUARDIAN){
                    String url = null;
                    JsonArray assets = jsonObject.getAsJsonArray("assets");
                    for (JsonElement asset : assets){
                        JsonObject assetObject = asset.getAsJsonObject();
                        if(assetObject == null || !assetObject.has("type")
                                || !assetObject.get("type").getAsString().equals("video")
                                || !assetObject.has("file")){
                            continue;
                        }
                        url = assetObject.get("file").getAsString();
                    }

                    if(url == null){
                        url = data.get("url").getAsString();
                    }
                    newsBody.add(new NewsElementVideo(
                            url,
                            data.get("html").getAsString(),
                            videoSource
                            ));
                }
            }
        }

        return newsBody;
    }
}
