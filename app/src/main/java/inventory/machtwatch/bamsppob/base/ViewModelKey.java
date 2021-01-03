package inventory.machtwatch.bamsppob.base;


import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * A dagger custom scope of such dependencies is tied to the scope of Fragment.
 *
 * As short information, Scope is the lifetime of any object/dependency.
 * <p>
 * You can read more about it in the
 * <a href="https://github.com/codepath/android_guides/wiki/Dependency-Injection-with-Dagger-2">Dependency
 * Injection with Dagger 2</a>.
 *
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {

    Class<? extends ViewModel> value();
}
