package com.example.liew.idelivery.Model;

import java.util.List;

/**
 * Created by kundan on 12/21/2017.
 */

public class MyResponse {

    public long multicast_id;
    public int success;
    public int failure;
    public int canonical_ids;
    public List<Result> results;
}
