package com.github.masaliev.guardianclient.data.model;

import java.util.List;

public class PaginationResult<T> {

    public int currentPage;

    public int totalPages;

    public List<T> results;
}
