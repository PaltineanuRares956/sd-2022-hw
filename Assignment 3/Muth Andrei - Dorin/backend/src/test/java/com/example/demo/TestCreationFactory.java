package com.example.demo;

import com.example.demo.app.dto.AppDto;
import com.example.demo.app.model.App;
import com.example.demo.app.model.AppType;
import com.example.demo.thread.comment.dto.CommentDto;
import com.example.demo.thread.comment.dto.PostCommentDto;
import com.example.demo.thread.dto.CreateThreadDto;
import com.example.demo.thread.dto.ThreadDto;
import com.example.demo.thread.model.Thread;
import com.example.demo.user.dto.UserListDto;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.demo.app.model.EType.GAME;
import static java.util.stream.Collectors.toList;

public class TestCreationFactory {

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls) {
        return listOf(cls, (Object[]) null);
    }

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls, Object... parameters) {
        int nrElements = new Random().nextInt(10) + 5;
        Supplier<?> supplier;

        if (cls.equals(UserListDto.class)) {
            supplier = TestCreationFactory::newUserListDto;
        } else if (cls.equals(App.class)) {
            supplier = TestCreationFactory::newApp;
        } else if (cls.equals(AppDto.class)) {
            supplier = TestCreationFactory::newAppDto;
        } else if (cls.equals(ThreadDto.class)) {
            supplier = TestCreationFactory::newThreadDto;
        } else if (cls.equals(CreateThreadDto.class)) {
            supplier = TestCreationFactory::newCreateThreadDto;
        } else if (cls.equals(CommentDto.class)) {
            supplier = TestCreationFactory::newCommentDto;
        } else if (cls.equals(Thread.class)) {
            supplier = TestCreationFactory::newThread;
        }
        else {
            supplier = () -> new String("You failed.");
        }

        Supplier<?> finalSupplier = supplier;
        return IntStream.range(0, nrElements).mapToObj(i ->
                        (T) finalSupplier.get()
                ).collect(Collectors.toSet()) // remove duplicates in case of Long for example
                .stream().collect(toList());
    }

    private static UserListDto newUserListDto() {
        return UserListDto.builder()
                .id(randomLong())
                .name(randomString())
                .email(randomEmail())
                .build();
    }

    public static App newApp() {
        return App.builder()
                .id(randomLong())
                .name(randomString())
                .description(randomString())
                .price(randomFloat())
                .build();
    }

    public static AppDto newAppDto() {
        return AppDto.builder()
                .id(randomLong())
                .name(randomString())
                .description(randomString())
                .type("GAME")
                .price(randomFloat())
                .build();
    }

    public static CommentDto newCommentDto() {
        return CommentDto.builder()
                .text(randomString())
                .username(randomString())
                .build();
    }

    public static PostCommentDto newPostCommentDto() {
        return PostCommentDto.builder()
                .text(randomString())
                .threadId(randomString())
                .build();
    }

    public static CreateThreadDto newCreateThreadDto() {
        return CreateThreadDto.builder()
                .title(randomString())
                .initialComment(randomString())
                //.user(TestCreationFactory.newUserListDto())
                .build();
    }

    public static Thread newThread() {
        return Thread.builder()
                .id(randomString())
                .title(randomString())
                .build();
    }

    public static ThreadDto newThreadDto() {
        return ThreadDto.builder()
                .id(randomString())
                .title(randomString())
                .build();
    }

    public static float randomFloat() {
        return new Random().nextFloat();
    }

    public static String randomEmail() {
        return randomString() + "@" + randomString() + ".com";
    }

    public static byte[] randomBytes() {
        byte[] bytes = new byte[Math.toIntExact(randomLong())];
        new Random().nextBytes(bytes);
        return bytes;
    }

    public static long randomLong() {
        return new Random().nextInt(1999);
    }

    public static Boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static int randomBoundedInt(int upperBound) {
        return new Random().nextInt(upperBound);
    }

    public static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}