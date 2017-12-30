package com.projet.florianepeltier.mobileproject.Model;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

/**
 * Created by root on 27/12/17.
 */

public class Prenom implements SortedListAdapter.ViewModel {
    private long id;
    private String intitule;
    private String requester;
    private long likes;
    private long dislikes;

    Prenom(long id, String intitule, String requester, long likes, long dislikes){
        super();
        this.id = id;
        this.intitule = intitule;
        this.requester = requester;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public long getLikes() {
        return likes;
    }

    void setLikes(long likes) {
        this.likes = likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    void setDislikes(long dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prenom prenom = (Prenom) o;

        return id == prenom.id && (intitule != null ? intitule.equals(prenom.intitule) : prenom.intitule == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (intitule != null ? intitule.hashCode() : 0);
        return result;
    }
}
