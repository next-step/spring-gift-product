package gift.common.resolver;

import gift.common.annotation.SortParam;
import gift.common.dto.SortInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SortParamArgumentResolver implements HandlerMethodArgumentResolver {
  private static final String SORT_PARAM_KEY = "sort";
  private static final String SORT_DELIMITER = ",";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(SortParam.class)
        && parameter.getParameterType().equals(SortInfo.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

    String sortParamValue = webRequest.getParameter(SORT_PARAM_KEY);
    return parseSortInfo(sortParamValue);
  }

  private SortInfo parseSortInfo(String sortParamValue) {
    String[] sortParams = sortParamValue.split(SORT_DELIMITER);
    String sortField = sortParams[0];
    boolean isAscending = sortParams.length < 2 || sortParams[1].equalsIgnoreCase("asc");
    return new SortInfo(sortField,isAscending);
  }
}
