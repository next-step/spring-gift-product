package gift.common.resolver;

import gift.common.annotation.PageParam;
import gift.common.dto.PageRequest;
import gift.common.dto.SortInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageParamArgumentResolver implements HandlerMethodArgumentResolver {
  private static final String OFFSET_PARAM_KEY = "page";
  private static final String PAGE_SIZE_PARAM_KEY = "size";
  private static final String SORT_PARAM_KEY = "sort";
  private static final String SORT_DELIMITER = ",";
  private static final int DEFAULT_OFFSET = 0;
  private static final int DEFAULT_PAGE_SIZE = 10;
  private static final String DEFAULT_SORT_FIELD = "id";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(PageParam.class)
        && parameter.getParameterType().equals(PageRequest.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

    return parsePageRequest(webRequest);
  }

  private PageRequest parsePageRequest(NativeWebRequest webRequest) {
    int offset = parseOffset(webRequest.getParameter(OFFSET_PARAM_KEY));
    int pageSize = parsePageSize(webRequest.getParameter(PAGE_SIZE_PARAM_KEY));
    SortInfo sortInfo = parseSortInfo(webRequest.getParameter(SORT_PARAM_KEY));

    return new PageRequest(offset, pageSize, sortInfo);
  }

  private int parseOffset(String offsetParam) {
    if (offsetParam == null || offsetParam.isEmpty()) {
      return DEFAULT_OFFSET;
    }
    try {
      int offset = Integer.parseInt(offsetParam);
      return Math.max(0, offset);
    } catch (NumberFormatException e) {
      return DEFAULT_OFFSET;
    }
  }

  private int parsePageSize(String pageSizeParam) {
    if (pageSizeParam == null || pageSizeParam.isEmpty()) {
      return DEFAULT_PAGE_SIZE;
    }
    try {
      int pageSize = Integer.parseInt(pageSizeParam);
      return Math.max(1, pageSize);
    } catch (NumberFormatException e) {
      return DEFAULT_PAGE_SIZE;
    }
  }

  private SortInfo parseSortInfo(String sortParamValue) {
    if (sortParamValue == null || sortParamValue.isEmpty()) {
      return new SortInfo(DEFAULT_SORT_FIELD, true);
    }

    String[] sortParams = sortParamValue.split(SORT_DELIMITER);
    String sortField = sortParams[0].trim();
    boolean isAscending = sortParams.length < 2 || sortParams[1].trim().equalsIgnoreCase("asc");

    return new SortInfo(sortField, isAscending);
  }
}